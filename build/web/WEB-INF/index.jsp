<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TodoList App</title>

        <!--Importar o Bootstrap-->
        <!-- CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">

        <!-- jQuery and JS bundle w/ Popper.js -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ho+j7jyWK8fNQe+A12Hb8AhRq26LrZ/JpcUGGOn+Y7RsweNrtN/tE3MoK7ZeZDyx" crossorigin="anonymous"></script>

        <style>
            .done {
                text-decoration: line-through;
            }
        </style>
    </head>
    <body class="container mt-5">
        <h1>TodoList App!</h1>

        <button class="btn btn-success mb-4" data-toggle="modal" data-target="#createModal">+ Adicionar Tarefa</button>
        <table class="table">
            <thead>
                <tr>
                    <th>Tarefa</th>
                    <th></th>
                </tr>
            </thead>

            <tbody>
                <c:forEach items="${notDone}" var="t">
                    <tr>
                        <td><c:out value="${t.title}" /></td>
                        <td>
                            <div class="row">
                                <form action="/todo-list/">
                                    <input type="hidden" name="id" value="${t.id}" />
                                    <input type="hidden" name="action" value="done" />
                                    <button type="submit" class="btn btn-primary">Concluir</button>
                                </form>

                                <button class="btn btn-secondary ml-2" data-toggle="modal" data-target="#updateModal"
                                        onclick="onUpdate(${t.id}, '${t.title}')">
                                    Editar
                                </button>
                                <button class="btn btn-danger ml-2" data-toggle="modal" data-target="#deleteModal"
                                        onclick="onDelete(${t.id}, '${t.title}')">
                                    Deletar
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
                <c:forEach items="${done}" var="t">
                    <tr class="table-secondary">
                        <td class="done"><c:out value="${t.title}" /></td>
                        <td>
                            <div class="row">
                                <form action="/todo-list/">
                                    <input type="hidden" name="id" value="${t.id}" />
                                    <input type="hidden" name="action" value="notDone" />
                                    <button type="submit" class="btn btn-secondary">Desfazer</button>
                                </form>
                                <button class="btn btn-danger ml-2" data-toggle="modal" data-target="#deleteModal"
                                        onclick="onDelete(${t.id}, '${t.title}')">
                                    Deletar
                                </button>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Modal: create -->
        <div class="modal fade" id="createModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Adicionar Tarefa</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/todo-list/">
                            <div class="form-group">
                                <label for="title">Título da Tarefa</label>
                                <input class="form-control" id="title" name="title">
                            </div>
                            <input type="hidden" name="action" value="create" />
                            <button type="submit" class="btn btn-success">Adicionar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal: update -->
        <div class="modal fade" id="updateModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Editar Tarefa</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/todo-list/">
                            <div class="form-group">
                                <label for="title">Título da Tarefa</label>
                                <input class="form-control" id="title" name="title">
                            </div>
                            <input type="hidden" name="id" value="" />
                            <input type="hidden" name="action" value="update" />
                            <button type="submit" class="btn btn-success">Atualizar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal: delete -->
        <div class="modal fade" id="deleteModal">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Certeza que deseja remover?</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form action="/todo-list/">
                            <div class="form-group">
                                <label for="title">Título da Tarefa</label>
                                <input class="form-control" id="title" name="title" readonly="">
                            </div>
                            <input type="hidden" name="id" value="" />
                            <input type="hidden" name="action" value="delete" />
                            <button type="submit" class="btn btn-danger">Deletar</button>
                            <button type="submit" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            function onUpdate(id, title) {

                var idEl = document.querySelector("#updateModal input[name=id]");
                var titleEl = document.querySelector("#updateModal input[name=title]");

                idEl.value = id;
                titleEl.value = title;

            }

            function onDelete(id, title) {

                var idEl = document.querySelector("#deleteModal input[name=id]");
                var titleEl = document.querySelector("#deleteModal input[name=title]");

                idEl.value = id;
                titleEl.value = title;

            }
        </script>
    </body>
</html>
