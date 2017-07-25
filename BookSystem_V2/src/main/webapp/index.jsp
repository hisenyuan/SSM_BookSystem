<html xmlns:th="http://www.w3.org/1999/xhtml">
<!--最好是创建一个纯净的html文件，不然会报错-->
<head>
    <title>Reading List</title>
    <link rel="stylesheet" th:href="@{/style.css}"></link>
</head>
<body>
<h2>Your Reading List</h2>
<div th:unless="${#lists.isEmpty(books)}">
    <dl th:each="book : ${books}">
        <dt class="bookHeadline">
            <span th:text="${book.title}">Title</span> by
            <span th:text="${book.author}">Author</span>
            (ISBN:<span th:text="${book.isbn}">ISBN</span>)
        </dt>
        <dd class="bookDecsription">
            <span th:if="${book.description}" th:text="${book.description}">Description</span>
            <span th:if="${book.description eq null}">No Description Available</span>
        </dd>
    </dl>
</div>
<div th:if="${#lists.isEmpty(books)}">
    <p>you have no book in you book list</p>
</div>

<hr />
<h3>Add a book</h3>
<form method="post">
    <label for="title">Title:</label>
    <input type="text" name="title" size="50" /><br />
    <label for="author">Author:</label>
    <input type="text" name="author" size="50" /><br />
    <label for="isbn">ISBN:</label>
    <input type="text" name="isbn" size="50" /><br />
    <label for="description">Description</label><br/>
    <textarea name="description" rows="5" cols="80"></textarea><br/>
    <input type="submit" />
</form>
</body>
</html>
