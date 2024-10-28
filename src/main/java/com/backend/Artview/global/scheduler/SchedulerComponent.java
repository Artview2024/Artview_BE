package com.backend.Artview.global.scheduler;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import com.backend.Artview.domain.exhibition.domain.ProgressType;
import com.backend.Artview.domain.exhibition.repository.CrawlingExhibitionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class SchedulerComponent {
    private final CrawlingExhibitionRepository crawlingExhibitionRepository;

    @Scheduled(cron = "${schedule.cron}", zone = "${schedule.zone}")
    @Transactional
    public void runScheduler() {

        LocalDate todayDate = LocalDate.now();
        log.info("Scheduler 실행 : " + todayDate);

        log.info("진행 예정인 정보 찾는 스케줄러 동작");
        crawlingExhibitionRepository.findAllByProgressType("0")
                .forEach(exhibition -> updateProgressType(exhibition, todayDate));

        log.info("진행 중인 정보 찾는 스케줄러 동작");
        crawlingExhibitionRepository.findAllByProgressType("1")
                .forEach(exhibition -> updateProgressType(exhibition, todayDate));
    }


    private void updateProgressType(CrawlingExhibition exhibition, LocalDate todayDate) {
        LocalDate startDate = convertToLocalDate(exhibition.getStartDate());
        LocalDate finishDate = convertToLocalDate(exhibition.getFinishDate());

        ProgressType newProgressType = checkProgressType(todayDate, startDate, finishDate);

        if (!compareProgressType(newProgressType, exhibition.getProgressType())) {
            exhibition.updateProgress(newProgressType.getCode());
        }

    }

    private boolean compareProgressType(ProgressType newProgressType, String progressType) {
        return newProgressType.getCode().equals(progressType);
    }

    private ProgressType checkProgressType(LocalDate todayDate, LocalDate startDate, LocalDate finishDate) {

        if ((todayDate.isEqual(startDate) || todayDate.isAfter(startDate)) && (todayDate.isEqual(finishDate) || todayDate.isBefore(finishDate))) {
            return ProgressType.ONGOING; //진행중으로 update
        } else if (todayDate.isAfter(startDate) && todayDate.isAfter(finishDate)) {
            return ProgressType.COMPLETED; //진행 완료로 update
        } else
            return ProgressType.UPCOMING;
    }


    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
