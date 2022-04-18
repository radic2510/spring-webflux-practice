
### [Daily] 2022.04.19

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
---

