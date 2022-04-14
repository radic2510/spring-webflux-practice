package com.codestates.edastudy;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

import java.time.Duration;

public class Week1Test {

    //1. ["Blenders", "Old", "Johnnie"] 와 "[Pride", "Monk", "Walker"] 를
    // 순서대로 하나의 스트림으로 처리되는 로직 검증
    @Test
    public void concatWithDelayTest() {
        Flux<String> names1 = Flux.just("Blenders", "Old", "Johnnie")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> names2 = Flux.just("Pride", "Monk", "Walker")
                .delayElements(Duration.ofSeconds(1));
        Flux<String> names = Flux.concat(names1, names2)
                .log();

        StepVerifier.create(names)
                .expectSubscription()
                .expectNext("Blenders", "Old", "Johnnie", "Pride", "Monk", "Walker")
                .verifyComplete();
    }


    // 2. 1~100 까지의 자연수 중 짝수만 출력하는 로직 검증
    @Test
    public void filterOddNumberTest() {
        Flux<Integer> oddNumber = Flux.range(1, 100)
                .filter(i -> i % 2 == 0 )
                .log();

        oddNumber.subscribe(System.out::println);

        StepVerifier.create(oddNumber)
                .thenConsumeWhile(n -> n % 2 == 0)
                .verifyComplete();
    }


    // 3. “hello”, “there” 를 순차적으로 publish하여 순서대로 나오는지 검증
    @Test
    void sequentialPublishTest() {
        Flux<String> flux = Flux.just("hello", "there")
                        .log();

        StepVerifier.create(flux)
                .expectNext("hello")
                .expectNext("there")
                .verifyComplete();

    }


    // 4. 아래와 같은 객체가 전달될 때 “JOHN”, “JACK” 등 이름이 대문자로 변환되어 출력되는 로직 검증
    //      Person("John", "[john@gmail.com](mailto:john@gmail.com)", "12345678")
    //      Person("Jack", "[jack@gmail.com](mailto:jack@gmail.com)", "12345678")
    @Test
    void convertNameUppercaseTest() {
        Person john = new Person("John", "[john@gmail.com](mailto:john@gmail.com)", "12345678");
        Person jack = new Person("Jack", "[jack@gmail.com](mailto:jack@gmail.com)", "12345678");

        Flux<Person> flux = Flux.just(john, jack)
                .map(p -> {
                    p.setName(p.getName().toUpperCase());
                    return p;
                })
                .log();

        flux.subscribe(p -> System.out.println(p.getName()));

        StepVerifier.create(flux)
                .expectNextMatches(p -> p.getName().equals("JOHN"))
                .expectNextMatches(p -> p.getName().equals("JACK"))
                .verifyComplete();

    }


    // 5. ["Blenders", "Old", "Johnnie"] 와 "[Pride", "Monk", "Walker”]를 압축하여 스트림으로 처리 검증
    //    -> 예상되는 스트림 결과값 ["Blenders Pride", "Old Monk", "Johnnie Walker”]
    @Test
    void strZipTest() {
        Flux<String> names1 = Flux.just("Blenders", "Old", "Johnnie");
        Flux<String> names2 = Flux.just("Pride", "Monk", "Walker");

        Flux<String> zippedNames = Flux.zip(names1, names2, (p1, p2) -> p1 + " " + p2)
                .log();


        StepVerifier.create(zippedNames)
                .expectNext("Blenders Pride")
                .expectNext("Old Monk")
                .expectNext("Johnnie Walker")
                .verifyComplete();

    }


    // 6. ["google", "abc", "fb", "stackoverflow”] 의 문자열 중 5자 이상 되는 문자열만 대문자로 비동기로 치환하여 1번 반복하는 스트림으로 처리하는 로직 검증
    //    -> 예상되는 스트림 결과값 ["GOOGLE", "STACKOVERFLOW", "GOOGLE", "STACKOVERFLOW"]
    @Test
    void asyncAndLoopTest() {
        Flux<String> flux = Flux.just("google", "abc", "fb", "stackoverflow")
                .filter(s -> s.length() >= 5)
                .flatMap(s -> Flux.just(s.toUpperCase()))
                .subscribeOn(Schedulers.boundedElastic())
                .repeat(1)
                .log();

        StepVerifier.create(flux)
                .expectNext("GOOGLE")
                .expectNext("STACKOVERFLOW")
                .expectNext("GOOGLE")
                .expectNext("STACKOVERFLOW")
                .verifyComplete();
    }
}
