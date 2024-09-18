package com.backend.Artview.domain.communication.Repository;

import com.backend.Artview.domain.communication.domain.Communications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CommunicationsCustomQueryRepository {

//    private final JPAQueryFactory queryFactory;
//
//    public Slice<Communications> searchBySlice(Long cursor, PageRequest pageRequest)
//    {
//        return queryFactory.selectFrom(communications)
//                .where(
//                        // no-offset 페이징 처리
//                        ltStoreId(lastStoreId),
//
//                        // 기타 조건들
//                        store.isAssigned.eq(true),
//                        eqCategory(condition.getCategoryIds()),
//                        eqConvenience(condition.getConvenienceIds())
//                )
//                .orderBy(store.id.desc())
//                .limit(pageRequest.getPageSize()+1)
//                .fetch();
//    }


}
