let index = {
    init: function() {
        $("#btn-save").on("click", () => { //function(){} 대신에 ()=>를 쓰는 이유는 this를 바인딩하기 위해서
            this.save();
        });
        $("#btn-delete").on("click", () => {
            this.deleteById();
        });
        $("#btn-update").on("click", () => {
            this.update();
        });
    },

    save: function (){
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };

        $.ajax({
            type:"POST",
            url:"/api/board",
            data:JSON.stringify(data), //http body 데이터. js의 data를 json형식으로 변환해서 자바도 이해할 수 있게 한다
            contentType:"application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
            dataType:"json" //요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열. (생긴게 json이라면)=> js오브젝트로 변경
        }).done(function (resp){
            alert("글쓰기가 완료되었습니다.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    },

    deleteById: function (){
        let id = $("#id").text();
        $.ajax({
            type:"DELETE",
            url:"/api/board/"+id,
            dataType: "json"
        }).done(function (resp){
            alert("삭제가 완료되었습니다.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    },

    update: function (){
        let id = $("#id").val();
        let data = {
            title: $("#title").val(),
            content: $("#content").val(),
        };
        console.log("update");

        $.ajax({
            type:"PUT",
            url:"/api/board/"+id,
            data:JSON.stringify(data), //http body 데이터. js의 data를 json형식으로 변환해서 자바도 이해할 수 있게 한다
            contentType:"application/json; charset=utf-8", //body 데이터가 어떤 타입인지(MIME)
            dataType:"json" //요청을 서버로 해서 응답이 왔을 때 기본적으로 모든 것이 문자열. (생긴게 json이라면)=> js오브젝트로 변경
        }).done(function (resp){
            alert("수정이 완료되었습니다.");
            location.href="/";
        }).fail(function (error){
            alert(JSON.stringify(error));
        });

    },

}

index.init();