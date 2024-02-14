//삭제 기능
const deleteBtn = document.getElementById('delete-btn');

if(deleteBtn) {
    deleteBtn.addEventListener('click', e => {
        let id = document.getElementById('article-id').value;
        fetch(`/api/articles/${id}`, {
            method: 'DELETE'
        })
        .then(() => {
            alert('삭제가 완료되었습니다.');
            location.replace('/articles');
        })
    })
}

//수정기능
const modifyBtn = document.getElementById('modify-btn');

if(modifyBtn) {
    modifyBtn.addEventListener('click', e => {
        let params = new URLSearchParams(location.search);
        let id = params.get('id');

        fetch(`/api/articles/${id}`, {
            method: 'PUT',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
        .then(() => {
            alert("수정이 완료되었습니다.");
            location.replace(`/articles/${id}`);
        })
    })
}

// 등록기능
const createBtn = document.getElementById('create-btn');

if(createBtn) {
    createBtn.addEventListener('click', e => {
        fetch(`/api/articles`, {
            method: 'POST',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                title: document.getElementById('title').value,
                content: document.getElementById('content').value
            })
        })
        .then(() => {
            alert("등록이 완료되었습니다.");
            location.replace(`/articles`);
        })
    })
}

// 스프링부트 따라하면 괜찮을 만한 블로그
// https://congsong.tistory.com/25

//템플릿 리터럴 백틱`` 달러${}  사용법
//ES6부터 새로 도입된 문자열 표기법이다.
//문자열 생성시 따옴표 대신, 백틱(`)을 사용한다.
//1) 줄바꿈
var str01 = `
hi!
    it's me!
        javascript
        so cool!`;
//var str_01 = "Hi! \n\t It's me! \n\t\t\t JavaScript! \n So Cool!";

//2) 표현식 삽입 ${ } 사이에 변수나 연산 등을 삽입할 수 있게 되었다.
var name = `사과`;
var price = 1000;
var num = 5;
var print = `${name}의 구매가는 ${price * num}원 입니다.`;
