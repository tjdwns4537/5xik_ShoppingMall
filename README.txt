쇼핑몰 제작

2022.7.3



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



 
