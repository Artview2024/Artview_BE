# CLAUDE.md

이 파일은 이 저장소에서 코드 작업을 할 때 Claude Code (claude.ai/code)에게 가이드를 제공합니다.

## 언어 설정
**중요**: 모든 응답과 대화는 한글로 진행해야 합니다. 사용자와의 모든 커뮤니케이션은 한국어를 사용하세요.

## 프로젝트 개요

Artview는 실시간 전시 관람 기록 및 공유 앱 서비스를 위한 Spring Boot 백엔드 서비스입니다. Java 17과 Spring Boot 3.3.0으로 구축된 REST API 백엔드입니다.

## 개발 명령어

### 빌드 및 테스트
- **테스트 제외 빌드**: `./gradlew build -x test`
- **테스트 실행**: `./gradlew test`
- **로컬 애플리케이션 실행**: `./gradlew bootRun`

### Docker 개발
- **모든 서비스 시작**: `docker compose up -d`
- **모든 서비스 중지**: `docker compose down`
- **로그 확인**: `docker compose logs -f [service_name]`
- **데이터베이스 접근**: 로컬에서는 3307 포트, Docker에서는 3306 포트에서 MySQL 실행

### 데이터베이스
- 로컬 MySQL: `localhost:3307/artview` (root/1234)
- Docker MySQL: `localhost:3306/artview` (root/1234)

## 아키텍처

### 도메인 주도 구조
코드베이스는 명확한 분리를 통한 도메인 주도 아키텍처를 따릅니다:

```
src/main/java/com/backend/Artview/
├── domain/              # 비즈니스 도메인
│   ├── auth/           # 인증 및 JWT
│   ├── communication/  # 커뮤니티 기능 (게시물, 댓글, 좋아요)
│   ├── exhibition/     # 전시 데이터 및 검색
│   ├── users/          # 사용자 관리 및 프로필
│   └── myReviews/      # 사용자 리뷰 및 콘텐츠
├── global/             # 공유 컴포넌트
│   ├── code/           # 응답 코드
│   ├── constant/       # 상수
│   ├── exception/      # 글로벌 예외 처리
│   └── response/       # API 응답 래퍼
```

### 주요 기술
- **인증**: Kakao OAuth2 + JWT
- **데이터베이스**: JPA/Hibernate를 사용하는 MySQL 8.0
- **파일 저장**: AWS S3
- **모니터링**: Prometheus + Grafana
- **캐싱**: Redis

### 도메인 책임
- **auth**: 카카오 로그인, JWT 토큰, 리프레시 토큰
- **communication**: 소셜 기능 (게시물, 댓글, 좋아요, 스크랩)
- **exhibition**: 전시 데이터 관리 및 검색
- **users**: 사용자 프로필, 팔로우 시스템, 관심사
- **myReviews**: 개인 전시 리뷰 및 콘텐츠

### 오류 처리
각 도메인은 다음 패턴을 따르는 자체 ErrorCode enum과 Exception 클래스를 가집니다:
- `[Domain]ErrorCode.java` - 오류 코드 정의
- `[Domain]Exception.java` - 도메인별 예외
- `global/exception/`의 글로벌 예외 핸들러

### API 응답 패턴
모든 엔드포인트는 `SuccessCode`와 도메인별 오류 코드의 표준화된 성공/오류 코드와 함께 `ApiResponse<T>` 래퍼를 사용합니다.

## 모니터링 스택
- **애플리케이션**: `/actuator/*`의 Spring Actuator 엔드포인트
- **Prometheus**: `http://localhost:9090`
- **Grafana**: `http://localhost:3001` (admin/admin)
- **Redis**: 6379 포트

## CI/CD
GitHub Actions 워크플로우는 main/develop 브랜치에 push/PR 시 빌드하고 Docker Hub를 통해 AWS EC2에 배포합니다.

