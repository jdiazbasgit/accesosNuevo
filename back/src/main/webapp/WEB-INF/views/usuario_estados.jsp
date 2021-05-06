<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cuadrante</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="./resources/css/usuario_estados.css">
    <script src="./resources/usuario_estados.js"></script>
</head>

<body>
    <div class="container-fluid">
        <div class="jumbotron">
            <h1>Lista de jornadas de los empleados</h1>
        </div>

        <table border="1" class="table table-striped table-dark" id="mi-table">
            <tr value="init">
                <th>USUARIO</th>
                <th>JORNADA</th>
                <th>ESTADO</th>
                <th>CALENDARIO</th>
            </tr>
        </table>

        <div class="jumbotron-fluid" id="mi-jumbotron">
            <h2>Nueva asignacion</h2>

            <input type="hidden" name="" id="idAModificar" value="-1">
            <select name="empleado" id="empleados">
                <option value="empty">Selecciona empleado...</option>
            </select>
            <select name="jornada" id="jornadas"> 
                <option value="empty">Selecciona jornada...</option>
            </select>
            <select name="estado" id="estados"> 
                <option value="empty">Selecciona estado...</option>
            </select>
            <select name="calendario" id="calendarios"> 
                <option value="empty">Selecciona calendario...</option>
            </select>
            <button type="button" class="btn btn-success" onclick="guardarRegistro()" value="0" id="bt-guardar">Guardar asignacion</button>
        </div>
    </div>
</body>

</html>