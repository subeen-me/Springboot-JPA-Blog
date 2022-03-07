<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../layout/header.jsp" %>

<div class="container">

    <form>
        <input type="hidden" id="id" value="${board.id}" />
        <div class="form-group">
            <label for="title">Title</label>
            <input value="${board.title}" type="text" class="form-control" placeholder="Enter Title" id="title">
        </div>
        <div class="form-group">
            <label for="content">Content:</label>
            <textarea class="form-control summernote" rows="5" id="content">${board.content}</textarea>
        </div>
    </form>

    <button id="btn-update" class="btn btn-primary">수정완료</button>

</div>
<script>
    $('.summernote').summernote({
        placeholder: '어떤 글을 쓰고 싶으신가요?',
        tabsize: 2,
        height: 300
    });
</script>
<script src="/js/board.js"></script>
<%@ include file="../layout/footer.jsp" %>

