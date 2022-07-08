쇼핑몰 제작

[ 2022.7.3 ]

 - 구분
 1) Serivce : Logic 구현
 2) Repository : 데이터를 저장
 3) Controller : 가공된 데이터에 대한 결과를 View에 전달함으로써 웹에 출력해줌



[ 컴포넌트 스캔과 자동 의존관계 설정방법으로 스프링 빈 등록하기 ]

 - 스프링 컨테이너를 왜 사용할까?
: MemberService 에서 실행되는 기능들은 별 다른 작업이 없기 때문에 굳이 여러 객체를 만들어서 사용할 이유가 없다.
그냥 하나의 객체를 만들어서 그 객체를 공유해서 사용하면 되는것이다.
그래서 이렇게 공유해서 MemberService 기능을 사용하기 위해 컨테이너를 사용하는 것이다.


 * 생성자의 Autowired 역할
: Spring 에서 memberservice 객체를 컨테이너에서 관리하는 Autowired annotation 이 붙은 이 생성자를 연결시켜준다.
 단, 이때 MemberService 클래스에는 순수 자바이면 안되고, @Service annotation 이 붙어있어야 한다.

 => 만약 생성자가 하나만 있으면 AutoWired 생략 가능 

 - Controller <-> Service 구동과정
1) MemberService 에 대한 생성자를 container 에 Autowired 로 등록함으로써 컨테이너가 관리하도록 해준다.
2) MemberService 클래스에 @Service annotation을 붙쳐줌
3) spring 이 구동될때 @Service를 읽고 spring container 에 MemberService 를 등록해준다.
4) Controller 의 Autowired 생성자에 container 에 있는 MemberService를 주입해준다.

 * 이러한 부분은 Repository도 동일하다.

 - Service <-> Repository 구동과정
1) MemberService 클래스 안에 있는 MemberService 생성자에서 Repository에 대한 객체를 넣어준다.
2) 이때 MemberService 클래스 안에 있는 MemberServcie 생성자에 @Autowired annotation 을 붙쳐준다.
3) spring 이 구동이 될 때 @Repository를 읽고 이 생성자에 Container 에 있는 MemberRepository 를 주입해준다.




[ Dependency Injection ] 
: 앞의 과정을 보고 정리해보면,
 컨트롤러가 생성이 될때, 스프링 빈에 등록되어 있는 MemberService 객체를 Controller 에 연결해주는 것을 의존관계주입이라고 한다. 그리고 이를 DI 라고 부른다.

 * 생성자 Injection 을 사용하는 이유

 1) Setter Injection 
  : set할때 Autowired 가 되는 것인대,
    그리고 set함수는 바꿀 일이 없긴하지만 혹시나 수정해야 될 경우를 대비해 public 으로 선언하면서 노출된다느 점이다.
 2) Field Injection
  : 해당 필드의 객체를 바꿀수 있는 방법이 없어서 잘 사용하지 않는다.
 3) 생성자 Injection
  : 의존관계가 실행중에 동적으로 변하는 경우가 거의 없으므로 생성자 주입을 주로 사용한다.

  




*** Service, Repository,Controller 는 Component 에 다 들어있기때문에 Component annotation 을 붙쳐줘도 된다. ***

*** Component 구동 과정

1) Spring 이 구동될 때, @Component annotation 을 붙은 것을 읽음
2) 해당 annotation 붙은 객체를 전부 Spring container 에 등록을 함
3) @Autowired 를 가지고 해당 @Component 에 대해 연관관계를 만들어 준다.
 => 이 과정을 통해 MemberController 가 MemberService 를 쓸 수 있고, MemberService가 MemberRepository를 쓸 수 있음

*** 단, Spring bean 으로 등록되는 것은 xik.ShoppingMall; 라는 패키지의 하위 목록에 있는 것들만 해당된다.






[ Java Code 로 스프링 빈 등록하기 ]

: 직접 설정 파일에 등록하는 방법

1) SpringConfig class 생성 ( 이름은 맘대로 지어도 됨 )
2) @Configuration annotation 달아준다.
3) @Bean annotation 에 MemberRepository 생성자에 return new MemoryMemberRepository() 해준다.
4) @Bean annotation 달고 MemberService 생성자에 return new MemberService(MemberRepository()) 를 해준다.
 -> 이때, MemberService 는 MemberRepository에 연결되야하기 때문에 MemberRepository 를 넣은 것이다.

* 단, Controller 와 연결된 부분은 자바로 어떻게 할 수 없기 때문에 Autowired 로 해준다.



ㅇ  [ 차이점 ]

1) 컴포넌트 스캔 : 실제 실무에서 주로 사용하며, 정형화된 컨트롤러/서비스/리포지토리 같은 코드는 컴포넌트 스캔을 사용한다.
2) 자바 코드 : 정형화되지 않거나 상황에 따라 구현 클래스를 변경해야 하는 경우 자바 코드로 설정을 통해 스프링 빈 등록을 한다.

 *** 예를 들어, Repository 를 임의로 선정하고 구현해야하는 경우에 나중에 결국 바꿔줘야 하므로
  자바 코드로 설정을 통해 스프링 빈 등록을 하는 것이다. 그래서 이를 통해 다른 코드를 전혀 손 댈 필요없이
  설정 파일만 조금 수정해주면 되는 것이다. ***






ㅇ memberCheck 구동과정

1) members 주소를 입력받음
2) memberService 에 입력된 데이터를 전부 리스트에 넣어준다.
3) Model 에 members 라는 속성 이름으로 해당 리스트를 넣어준다.
 -> 즉 Model 의 큰 박스 안에 members 라는 작은 박스에 리스트를 담아둔 것이다.

 * html 파일에서 $의 의미는 모델안에 있는 값을 꺼내오는 것이다. 그래서 $members를 하게되면
   Model 안에 members 이름을 가진 작은 박스를 꺼내오는 것이다.


[ 2022.07.04 ]

 !! Spring을 왜 쓰는가? 
 : < 다형성 > 때문이다. 하나의 인터페이스를 여러 구현체가 상속받고 기능을 구현하는데에 있어서
 스프링의 DI,스프링 컨테이너를 통해 굉장히 편하게 할 수 있기 때문이다.

 !! OCP ( 개방 폐쇄 원리 ) : 확장에는 열려있고, 수정에는 닫혀있다.
  ==> 객체지향을 잘 활용을 하면 기능을 완전히 변경을 해도 조립하는 코드만 수정할 뿐 애플리케이션 전체를 수정할 필요가 없어진다.






ㅇ 스프링에서 DB 다루기

1) JDBC Template
 : 스프링에서는 중복되는 부분을 다 제거해서 Spring JDBC Template 이라는 기술을 제공하면서
 애플리케이션에서 데이터베이스로 SQL을 편리하게 보낼 수 있도록 만들었다.

2) JPA
 : SQL 을 JPA 라는 기술을 통해 DB에 등록, 수정, 삭제 등에 대한 쿼리를 다 관리해주는 방법이다.
   그래서 JPA 를 쓰게 되면 객체를 쿼리없에 바로 DB에 넣을 수 있다.

3) Spring Data JPA
 : JPA 를 편리하게 쓸 수 있도록 한번 감싼 기술




ㅇ 순수 JDBC 다루기	--> 실제 코드 작성은 하지 않고 어떤 방식인지만 파악

 - 환경 설정
 1) jdbc / h2database 와 관련된 라이브러리 추가
 2) application.properties에 설정정보를 입력


 - 사용
 1) 기존에 만들어준 Repository Interface 를 상속받는 클래스 생성 후 해당 클래스에 다 작성한다.
 2) DataSource 타입의 객체를 만들어서 스프링을 통해서 주입을 받는다.
 3) datasource.getConnection() 을 통해 데이터베이스와 연결될 수 있는 소켓을 얻게 된다.
 4) 그럼 해당 소켓에 SQL 문을 날려서 전달해주면 된다.




ㅇ 스프링 Test

 - @SpringBootTest annotation 필요
 - 기존 자바 테스트랑 다른점
  : 객체를 직접 생성하고 호출하는것이 자바 테스트였다면 스프링에서는 스프링 컨테이너한테 서비스,리포지토리를 받아야한다.
  -> 내가 필요한것을 주입하고 끝나기때문에 그냥 @Autowired 를 달아준다.

 즉, @SpringBootTest의 역할
 : 스프링 컨테이너와 테스트를 함께 실행하여 실제 스프링 환경에서의 테스트가 가능해진다.


[ 2022.07.05 ]

 ㅇ @Transaction

 - Transactino 이란?
 : DB에 데이터를 INSERT 쿼리한 다음에 Commit을 해줘야 DB에 반영이 된다.
 근데 AUTO commit이면 자동으로 커밋되며, 커밋되기 전에는 DB에 반영되지 않는다.

 - @Transaction annotation
 : 테스트를 할 때 트랜잭션을 먼저 실행을 하고, DB에 데이터를 INSERT 쿼리를 하면서 다 넣은 다음에
 테스트가 끝나면 롤백을 해준다. 그러면 디비에 넣었던 데이터가 반영이 안되고 깔끔하게 다 지워진다.

 => 이를 통해 테스트를 반복할 수 있게 된다.

 * 즉 트랜잭션의 역할
 : 테스트를 할 수 있게끔 트랜잭션 안에서 쿼리를 다 실행시키고 디비에는 저장시키지 않음으로써 테스트를 계속 반복할 수 있게 해준다.
 단, 롤백을 이렇게 하는것은 테스트에 붙었을때만 해당하고, 서비스같은 곳에 붙으면 롤백하지 않는다 !!


 * 만약 다 트랜잭션 하는데 하나만 커밋하고 싶다면??
 : 해당함수에만 @Commit annotation 을 붙치면 된다.




ㅇ 단위테스트와 통합테스트 차이
 
 1) 단위 테스트 : 부분 기능만을 테스트 해보는 것으로 일반적으로 자바 코드로 구현하여 밀리세컨드의 초단위로 테스트 실행 가능하다.
 2) 통합 테스트 : 스프링의 전체적인 디비부터 기능까지 테스트해보는 것으로 스프링 어노테이션이 붙어서 시간이 오래걸린다.

 * 즉, 테스트는 순수 단위테스트가 훨씬 좋은 테스트이다.




[ 2022.07.06 ]

ㅇ 스프링 JdbcTemplate
: 스프링 jdbcTemplate 와 MyBatis 같은 라이브러리는 JDBC Api 에서 본 반복 코드를 대부분 제거해준다.
 하지만 SQL은 직접 작성해야한다.

ㅇ 사용방법
1) JdbcTemplate 타입의 변수를 생성 ( Injection 을 받을 수 있는 것은 아니다. ) ( 변수명을 jt라 가정 )
2) jdbcTemplate 클래스의 생성자에 DataSource 를 인자로 받게 만들고 이를 Injection 해준다 ( @Autowired )
3) 이때 생성자 내부 코드는 this.jdbcTemplate = new JdbcTemplate(dataSource) 이다.
4) memberRowMapper()를 구현해준다.
5) jdbcTemplate.query() 의 인자로 sql쿼리와 memberRowMapper() 를 받는다.


 * JdbcTemplate 이 코드가 jdbc로 구현한 코드보다 훨씬 짧은 이유는 디자인 패턴 중에 Template Method 패턴이라는게 있다.
   이를 통해 코드를 줄일 수 있게 된것이다. 



ㅇ JPA
: JdbcTemplate 을 사용해도 결국 해결되지 않는 부분이 있다.
 그것은 SQL 을 개발자가 직접 작성을 해야한다는 것이다. 그런데 JPA를 사용하면 SQL 쿼리도 JPA가 자동으로 처리를 해준다.
 이를 통해 개발 생산성을 크게 높일 수 있다. 마치 객체를 MemoryMemberRepository에서 처럼 JPA에 집어 넣으면
 JPA가 중간에서 DB에 SQL을 보내고 데이터를 처리하는것을 JPA가 다 처리를 해준다.
 그리고 단순히 SQL을 만들어 주는것을 넘어서서 객체 중심으로 고민을 할 수 있게 된다는 장점도 있다.
 그래서 SQL,데이터 중심의 설계에서 객체 중심의 설계로 패러다임을 전환할 수 있다.

 - 그래서 간단히 말하면 JPA 는 인터페이스를 제공해주는 것이고, 구현체로 여러가지가 있는데 여기서는 Hibernate 를 사용한다.

 1) dependency 
: implementation 'org.springframework.boot:spring-boot-starter-data-jpa' => jpa, jdbc 모두 포함하는 것

 2) application.properties 에 설정 추가
 - spring.jpa.show-sql=true 		
   ==> jpa 가 날리는 sql 볼 수 있음
 - spring.jpa.hibernate.ddl-auto = none 	
   ==> jpa 는 객체를 보고 테이블도 알아서 생성한다. 그런데 지금 테이블은 이미 만들어 져있으므로 만들지 않도록 none 설정 해준다.
	( create 하면 테이블 자동 생성 )

 3) Entity Mapping
 - ORM 의 방법인대 O : 객체, R : 관계, M : 매핑 이라해서 객체와 관계를 매핑해주는데 이를 엔티티 어노테이션으로 한다.
 - @Entitiy 를 붙치면 JPA가 관리하는 엔티티라는 의미이다.

	3-1) @Id : Primary Key이다. 데이터를 생성하면 DB에서 알아서 Id값을 생성해주는데 이를 아이덴티티라고한다.
		지금은 이 아이덴티티가 이뤄지고 있기때문에 아래의 GenerateValue를 설정해준다.
	3-2) @GeneratedValue(strategy = GenerationType.IDENTITY )
	3-3) @Column(name = "userName" )
		: 객체명은 name이지만 DB에서는 Column name 을 userName으로 쓰고 있는 경우 이렇게 설정한다.

 4) Repository 구현
 - EntityManager : JPA는 모든게 EntityManager 라는 것을 통해서 구현이 된다.
  ( 인젝션이 엔티티 매니저를 통해 이뤄진다. )
 - em.persist(member) : member 를 영속한다는 의미 , 이 함수하나로 인서트 쿼리가 다 만들어진다.
 - em.find(Member.class, id) : member의 id를 찾아준다.

 - em.createQuery( :select m from Member m", Member.class )
    : PK(id)가 아닌 나머지 객체에 대한 값을 찾을 때는 JPQL 함수를 써줘야한다. 보통 쿼리는 테이블을 대상으로 주고받게 되는데
     JPQL은 테이블이 아닌 객체를 대상 (엔티티를 대상) 으로 쿼리를 날리는 것이다. 그럼 이게 SQL로 번역이 된다.
	이때, 주의깊게 볼것은 select m 에서 m이 특이하다. 객체 자체를 다루는 것이다.
 
 5) Service 에 @Transactional 을 붙쳐줘야한다.
    JPA 는 모든 데이터를 다룰때 트랜잭션 안에서 이뤄지기 때문이다.








[ 2022. 07. 07 ]

ㅇ 스프링 데이터 JPA ( * import org.springframework.data.jpa.repository.JpaRepository 클래스 영역 )
: 개발해야할 코드를 줄어들게 해줌으로써 리포지토리에 클래스 없이 인터페이스만으로 개발 할 수 있게 해줍니다.
 즉, JPA를 편리하게 쓸 수 있도록 도와준다.

  * JpaRepository를 Command+클릭해서 들어가서 보면 어떤 메서드들이 기본적으로 제공되는지 다 볼 수 있다.


 - 방법
 1) JpaRepository를 상속 받는다.
	* 이때, Interface 가 Interface를 상속받으므로  implements가 아닌 extends로 상속받는다.
	* 형태 : JpaRepository<Member, 식별자(PK- Id)타입>, MemberRepository
		-> < > 와 MemberRepository 두개를 다중 상속받는 것이다.

 2) 내부에 오버라이딩 메서드를 작성해준다.
	* 이때, findByName과 같은 공통적으로 사용할 수 없는 메서드들을 작성해주는데 적으면 자동으로
	select에 대한 쿼리가 작성된다. ( JPQL문법 이해 필요 )
   
 3) 그러면 SpringDataJpa가 JpaRepository를 상속받고 있으면 구현체를 자동으로 만들어준다. 그러면 내가 스프링 빈에 등록하지 않
    아도 스프링 빈에 자동으로 등록된다.  

 => 즉, 원래 하던 방식인 MemberRepository 생성자 내부에 return new JpaMemberRepository(em) 과 같은 소스코드를
    작성하지 않아도 된다는 것이다.

 
 - 구현 동작 과정
 1) SpringDataJpa가 상속받은 리포지토리 인터페이스를 보고 스프링 빈을 자동으로 만든다.
 2) 프록시라는 기술을 통해 객체를 생성한다.
 3) 객체를 스프링 빈에 올려준다.
 4) 스프링 빈에 올라와있는 객체를 Injection을 통해 사용한다.
 

 - Spring Data Jpa 제공 기능
 1) 인터페이스를 통한 기본적인 CRUD
 2) findByName(), findByEmail() 처럼 메서드 이름만으로 조회 기능 제공한다.
 3) 페이징 기능 자동으로 제공한다.

	* 실무에선 스프링 데이터 JPA를 사용하고, 복잡한 동적 쿼리는 Querydsl이라는 라이브러리를 사용하면 된다.
	Querydsl을 사용하면 쿼리도 자바 코드로 안전하게 작성할 수 있고, 동적 쿼리도 편리하게 작성할 수 있다.
	이 둘을 통해서도 해결하기 어렵다면 JPA가 제공하는 네이티브 쿼리나 스프링 JdbcTemplate을 사용해야 한다.






 # Service 인터페이스 / 구현체 분리














