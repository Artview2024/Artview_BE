package com.backend.Artview.global.scheduler;

import com.backend.Artview.domain.exhibition.domain.CrawlingExhibition;
import com.backend.Artview.domain.exhibition.domain.ExhibitionType;
import com.backend.Artview.domain.exhibition.repository.CrawlingExhibitionRepository;
import com.backend.Artview.global.util.StringUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

import static com.backend.Artview.domain.exhibition.domain.ExhibitionType.*;

@Component
@Slf4j
@RequiredArgsConstructor
public class SchedulerComponent {
    private final CrawlingExhibitionRepository crawlingExhibitionRepository;
    private final StringUtil stringUtil;

    @Scheduled(cron = "0 0 0 * * *", zone = "${schedule.zone}")
    @Transactional
    public void runScheduler() {

        LocalDate todayDate = LocalDate.now();
        log.info("Scheduler 실행 : " + todayDate);

//        log.info("진행 예정인 정보 찾는 스케줄러 동작");
//        crawlingExhibitionRepository.findAllByProgressType(UPCOMING.getCode())
//                .forEach(exhibition -> updateProgressType(exhibition, todayDate, UPCOMING.getCode()));

        log.info("무료 정보 찾는 스케줄러 동작");
        crawlingExhibitionRepository.findAllByProgressType(FREE.getCode())
                .forEach(exhibition -> updateProgressType(exhibition, todayDate, FREE.getCode()));

        log.info("진행 중인 정보 찾는 스케줄러 동작");
        crawlingExhibitionRepository.findAllByProgressType(ONGOING.getCode())
                .forEach(exhibition -> updateProgressType(exhibition, todayDate,ONGOING.getCode()));
    }


    private void updateProgressType(CrawlingExhibition exhibition, LocalDate todayDate, String code) {
        LocalDate startDate = stringUtil.stringToLocalDate(exhibition.getStartDate());
        LocalDate finishDate = stringUtil.stringToLocalDate(exhibition.getFinishDate());

        ExhibitionType newProgressType = checkProgressType(todayDate, startDate, finishDate);

        if (newProgressType!=null && !compareProgressType(newProgressType, exhibition.getProgressType())) {
            exhibition.updateProgress(newProgressType.getCode());
        }

    }

    private boolean compareProgressType(ExhibitionType newProgressType, String progressType) {
        return newProgressType.getCode().equals(progressType);
    }

    private ExhibitionType checkProgressType(LocalDate todayDate, LocalDate startDate, LocalDate finishDate) {
        if (todayDate.isAfter(startDate) && todayDate.isAfter(finishDate)) {
            return ExhibitionType.COMPLETED; //진행 완료로 update
        } else return null;
    }

    private static boolean IsTypeFree(String code) {
        return Objects.equals(code, FREE.getCode());
    }


    private LocalDate convertToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
