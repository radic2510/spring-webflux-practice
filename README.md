### [Daily] 2차. 2022.04.19

>REST API 로 내부 통신하는 MSA 구축하기 

- 1차에서 구축한 서에서 InfoService server 로 내부 호출
- InfoService 는 호출 파라미터로 찾은 데이터의 직업 응답

#### (Spec):
> WebClient

#### (Request):
> GET localhost:8080/hello?name$name

#### (Response):
```
aplication/json
{
  "to": `${name}`,
  “job”: “”,
  "message": `hello ${name}
}
```

#### 결과물
- WebClient: [Router.java](https://github.com/radic2510/spring-webflux-practice/blob/main/src/main/java/com/codestates/edastudy/webClient/JobWebClient.java)
- Handler: [Handler.java](https://github.com/radic2510/spring-webflux-practice/blob/main/src/main/java/com/codestates/edastudy/handler/Handler.java)
- InternalService: [spring-webflux-practice-sub](https://github.com/radic2510/spring-webflux-practice-sub)

---

### [Daily] 1차. 2022.04.19

>REST API 에 반응하는 local 웹 서버 앱 구축하기

#### (Spec):
> Spring 5+, Java 11+, WebFlux, Functional EndPoint

#### (Request):
> GET localhost:8080/hello?name$name

#### (Response):
```
aplication/json
{
  "to": `${name}`,
  "message": `hello ${name}
}
```

#### 결과물
- Router: [Router.java](https://github.com/radic2510/spring-webflux-practice/blob/main/src/main/java/com/codestates/edastudy/router/Router.java)
- Handler: [Handler.java](https://github.com/radic2510/spring-webflux-practice/blob/main/src/main/java/com/codestates/edastudy/handler/Handler.java)

---

