
# Keystone Design Proeject

이 Repository는 키스톤 디자인 프로젝트용 입니다.

## 프로젝트 소개

해당 프로젝트는 소프트웨어 설계 공학과 Java 디자인 패턴을 활용해 거래소 시스템 구현하였습니다.
디자인 패턴은 총 8가지가 사용되었습니다.

## 실행 방법
```
git clone <주소> .
```

## 디렉토리 구조
```
├─src
│  ├─main
│  │  └─java
│  │      ├─auction
│  │      ├─auctionData
│  │      ├─ChargeManager
│  │      ├─CommandManage
│  │      │  ├─InventoryItems
│  │      │  ├─items
│  │      │  ├─TradeItems
│  │      │  └─Users
│  │      ├─itemInfos
│  │      ├─itemObserver
│  │      ├─sort
│  │      ├─swing
│  │      └─user
│  └─test
│      └─java
└─target
    ├─classes
    │  ├─auction
    │  ├─auctionData
    │  ├─ChargeManager
    │  ├─CommandManage
    │  │  ├─InventoryItems
    │  │  ├─items
    │  │  ├─TradeItems
    │  │  └─Users
    │  ├─itemInfos
    │  ├─itemObserver
    │  ├─sort
    │  ├─swing
    │  └─user
    └─test-classes
```

## 사용한 디자인 패턴

### 1. 데코레이터 패턴
수수료를 데코레이터 패턴을 이용하여 유용하게 적용합니다

### 2. 커맨드 패턴
커맨드 패턴을 통해 요청을 하는 부분과 실제로 요청을 수행하는 로직을 분리하여 유연성을 증가시키고 각 작업을 객체로 캡슐화 시킴으로써 다양한 작업에서 재사용 할 수 있습니다. 또한 새로운 커맨드를 추가하거나 기존 커맨드를 수정하는 것이 용이해집니다(유지보수성 증가).

### 3. 옵저버 패턴
아이템의 정보를 담고 있는 아이템 파일 시스템에서 아이템 변경/삭제가 일어날 시 아이템의 정보를 이용하는 유저 인벤토리 리스트, 거래 아이템 리스트의 아이템 정보를 업데이트 시킵니다.

### 4. 상태 패턴
거래소의 개방 / 폐쇄 상태를 상태 패턴을 적용하여 변경합니다

### 5. 빌더 패턴
유저, 아이템의 정보를 빌더 패턴을 이용하여 생성합니다

### 6. 퍼사드 패턴
Auction에서 서브시스템 간의 인터페이스 역할을 해줍니다

### 7. 템플릿 메소드 패턴
아이템 생성이 다양한 종류의 아이템을 일부 단계에서만 변화시키기 위해 적용하였습니다.

### 8. 전략 패턴
아이템 구매시 정렬 시스템에서 이름별, 가격별 등 정렬 / 역정렬을 위해 전략 패턴을 사용하였습니다.
