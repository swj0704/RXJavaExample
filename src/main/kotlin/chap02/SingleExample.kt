package chap02


import common.Order
import io.reactivex.Observable
import io.reactivex.Single

class SingleExample {
    // 1. 기존 Observable 에서 Single 객체로 변환하기
    // 기존 Observable 에서 첫 번째 값을 발행하면 onSuccess 이벤트를 호출한 후 종료
    fun first(){
        val source : Observable<String> = Observable.just("Hello Single")
        Single.fromObservable<Any>(source).subscribe{x -> println()}
    }

    // 2. single() 함수를 호출해 Single 객체 생성하기
    // just 로 생성된 Observable 에 single() 함수를 호출
    // single() 함수는 default value 를 인자로 갖는다.
    // Observable 에서 값이 발행되지 않을 때도 인자로 넣은 기본값을 대신 발행
    fun second(){
        Observable.just("Hello Single")
            .single("default item")
            .subscribe{x -> println()}
    }

    // 3. first() 함수를 호출해 Single 객체 생성하기
    // first() 함수를 호출하면 Observable 이 Single 객체로 변환된다.
    // 하나 이상의 데이터를 발행하더라도 첫번째 데이터 발행 후 onSuccess 이벤트가 발생
    fun third(){
        val colors = arrayOf("Red", "Blue", "Gold")
        Observable.fromArray(*colors)
            .first("default value")
            .subscribe{x -> println()}
    }

    // 4. empty Observable 에서 Single 객체 생성하기
    // 첫번째 데이터 발행 후 onSuccess 이벤트 발생
    // Observable 에서 값이 발행되지 않을 때도 기본값을 갖는 Single 객체로 변환할 수 있다.
    fun fourth(){
        Observable.empty<String>()
            .single("default value")
            .subscribe{x -> println()}
    }

    // 5. take() 함수에서 Single 객체 생성하기
    // Order 와 같은 사용자 정의 클래스도 Single 에서 사용 가능
    fun fifth(){
        Observable.just(Order("ORD-1"), Order("ORD-2"))
            .take(1)
            .single(Order("default value"))
            .subscribe{x -> println()}
    }

    fun error(){
        // Single 객체에 데이터 여러개를 넣으면 오류가 발생
        val source = Observable.just("Hello Single", "Error").single("default value")
        source.subscribe{x -> println()}
    }
}

fun main(){

}