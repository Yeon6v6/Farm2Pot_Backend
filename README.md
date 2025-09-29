## 1. User (사용자&인증)

| 구분 | 기능 | Method | Endpoint | 상세 설명 |
|------|------|--------|----------|------------|
| 인증 | 회원가입 | POST | `/api/register` | 사용자 계정 생성 (이름, 연락처, 비밀번호 등). |
| 인증 | 로그인 | POST | `/api/auth/login` | JWT 발급을 위한 로그인 및 인증. |
| 인증 | 토큰 재발급 | POST | `/api/auth/reissue` | Refresh Token을 사용하여 Access Token 재발급. |
| 프로필 | 내 정보 조회 | GET | `/api/user/{userId}` | 현재 로그인된 사용자의 기본 프로필 정보 조회. |
| 프로필 | 프로필 수정 | PUT | `/api/user/{userId}` | 이름, 연락처 등 프로필 정보 수정. |
| 배송지 관리 | 배송지 관리 | GET/POST/PUT/DELETE | `/api/users/me/addresses` | 등록된 배송지 목록 조회 및 관리. |

---

## 2. Subscription (구독&선택 박스)

| 구분 | 기능 | Method | Endpoint | 상세 설명 |
|------|------|--------|----------|------------|
| 구독 관리 | 구독 신청 | POST | `/api/subs` | 구독 상품 선택, 결제 정보 입력, 초기 배송지 및 취향 설문 등록. |
| 구독 관리 | 내 구독 현황 조회 | GET | `/api/subs/me` | 현재 상태, 다음 결제일, 다음 박스 선택 마감일 조회. |
| 구독 관리 | 구독 상태 변경 | PATCH | `/api/subs/{id}/status` | PAUSED(일시정지) 등으로 상태 변경. |
| 잔액 관리 | 내 사용 가능 잔액 조회 | GET | `/api/subs/me/balance` | 이번 구독 주기에서 사용할 수 있는 잔액 (KRW) 조회. |
| 잔액 관리 | 잔액 사용/적립 내역 | GET | `/api/subs/me/balance/history` | 구독료 납부(적립) 및 박스 구성(차감) 상세 내역. |
| 박스 구성 | 선택 가능 상품 목록 조회 | GET | `/api/subs/box/items` | 잔액 기준으로 선택 가능한 상품 목록 (상품명, 판매 금액, 재고 포함). |
| 박스 구성 | 내 박스 구성 임시 저장 | POST | `/api/subs/box/draft` | 선택한 상품 목록 및 총 예상 차감 금액 임시 저장. |
| 박스 구성 | 박스 구성 확정 | PUT | `/api/subs/box/confirm` | 임시 목록을 확정하고, 총 금액만큼 잔액을 차감하며 재고 예약 요청. |
| 배송/히스토리 | 구독 배송 히스토리 조회 | GET | `/api/subs/history` | 과거 배송 완료된 박스 목록 (페이징). |
| 배송/히스토리 | 배송 상세 및 추적 조회 | GET | `/api/subs/history/{orderId}` | 특정 박스 구성 내역, 최종 차감 금액, 배송 추적 정보 조회. |

---

## 3. Admin (관리자 기능)

| 구분 | 기능 | Method | Endpoint | 상세 설명 |
|------|------|--------|----------|------------|
| 상품/가격 관리 | 상품 등록/수정/삭제 | POST/PUT/DELETE | `/api/admin/products` | 상품명, 원산지, 재고, 소비자 판매 금액 (KRW) 관리. |
| 구독 정책 설정 | 구독 정책 설정 | PUT | `/api/admin/policies/subscription` | 월 납입액, 잔액 지급 비율, 이월/소멸 정책 설정. |
| 재고 관리 | 재고 등록/수정 | POST/PUT | `/api/admin/inventory/{productId}` | 상품별 현재 보유 재고 수량 업데이트. |
| 재고 관리 | 대량 업로드 | POST | `/api/admin/products/batch` | 상품 및 재고 정보 일괄 등록/수정. |
| 운영 관리 | 박스 선택 기간 설정 | PUT | `/api/admin/boxes/{boxId}/period` | 이번 달/주기 박스 선택 마감일 설정. |
| 운영 관리 | 자동 확정 트리거 | POST | `/api/admin/boxes/auto-confirm` | 마감일 이후 미선택 사용자 박스를 기본 박스로 자동 구성 및 금액 차감 처리 (배치 작업). |
| 운영 관리 | 운영 지표 조회 (KPI) | GET | `/api/admin/metrics` | 활성 구독 수, 잔액 소진율 등 주요 지표 조회. |

---

## 4. Core (공통/시스템)

| 구분 | 구성 요소 | 상세 설명 |
|------|----------|------------|
| 공통 모듈 | 에러 처리, 로깅, JWT 인증 | 표준화된 에러 응답 및 트랜잭션 로깅 시스템 구축. |
| 이벤트 처리 | Outbox 패턴 기반 이벤트 퍼블리셔 (Kafka) | 잔액 차감과 재고 예약 등 핵심 트랜잭션의 원자성 보장을 위한 비동기 이벤트 시스템. |
| 통계/집계 | 실시간 집계 (Redis), 지연 집계 (Batch) | 실시간 지표는 Redis로, 복잡한 통계 데이터는 배치 작업을 통해 별도 테이블에 저장. |
