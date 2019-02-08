var app = angular.module("mproducto", []);
app.controller("productoCtrl", ['$scope', '$http', function ($scope, $http)
{

// Variable inicial para el indice del array
    var idx=0;
    $scope.objmax= {};
    $scope.productos = [];
    $scope.productoActual = {};

    $scope.vista = "formulario";
    $scope.navegacion = true;
    $scope.edit = false;
    $scope.sololectura = true;
    $scope.botones ="noeditable";



    $http({
        method: 'GET',
        url: '/apiproducto/all'
    }).success(function (data) {
        $scope.productos = data;
    });

   //Datos para la lista desplegable
    $http({
        method: 'GET',
        url: '/apicategoria/all'
    }).success(function (data) {
        $scope.categorias = data;
    });


    $scope.listar = function () {
        $http({
            method: 'GET',
            url: '/apiproducto/all'
        }).success(function (data) {
            $scope.productos = data;
        });
    };


    $scope.nuevo = function () {
    	 	
    	$scope.productoActual.nombre = " ";
    	$scope.productoActual.stock = " ";
        $scope.botones ="editable";
        $scope.sololectura = false;
        $scope.navegacion = false;
    };

    $scope.buscar = function () {
        $scope.vista = "listado";
    };

    $scope.editar = function () {
        $scope.edit = true;
        $scope.botones ="editable";
        $scope.sololectura = false;
        $scope.navegacion = false;
    };

    $scope.cancelar = function () {
        $scope.edit = false;
        $scope.botones ="noeditable";
        $scope.sololectura = true;
        $scope.navegacion = true;
        $scope.listar();
        $scope.primero();
    };

    $scope.seleccionar = function (id) {
        var idx = -1;
        var productoArray = eval($scope.productos);
        for (var i = 0; i < productoArray.length; i++) {
            if (productoArray[i].id === id) {
                idx = i;
                $scope.productoActual = $scope.productos[idx];
                break;
            }
        }
        $scope.vista = "formulario";
    };



    $scope.guardar = function () {

        if ($scope.edit === false) {
            $http({
                method: 'POST',
                url: '/apiproducto/save',
                data: {
                    'nombre': $scope.productoActual.nombre,
                    'stock': $scope.productoActual.stock,
                    'categoria': $scope.productoActual.categoria
                     }
            }).success(function (data, status, headers, config) {

                    //toastr.success('Exito.', 'Registro Guardado', {timeOut: 50});
                    $scope.listar();
                })
                .error(function (data, status, headers, config) {
                    $scope.status = status;
                });
        }
        if ($scope.edit === true) {
            $http({

                method: 'PUT',
                url: '/apiproducto/update/' + $scope.productoActual.id,
                data: {
                	 'nombre': $scope.productoActual.nombre,
                     'stock': $scope.productoActual.stock,
                     'categoria': $scope.productoActual.categoria,
                }
            }).success(function (data, status, headers, config) {

                    // toastr.success('Exito.', 'Registro Actualizado', {timeOut: 50});
                    $scope.listar();
                })
                .error(function (data, status, headers, config) {
                    $scope.status = status;
                });

        }


        $scope.primero();
        $scope.botones ="noeditable";
        $scope.sololectura = true;
        $scope.navegacion = true;
    };

    $scope.borrar = function () {
        var x = confirm("Esta seguro de dar de baja este registro");
        if (x) {
            $http({
            	 method: 'PUT',
                 url: '/apiproducto/erase/' + $scope.productoActual.id,
                 
                data: {
                    'id' : $scope.productoActual.id,
                    'estado': $scope.productoActual.estado,
                }
            }).success(function (data, status, headers, config) {

                    //toastr.success('Exito.', 'Registro Eliminado', {timeOut: 50});
                    $scope.listar();
                })
                .error(function (data, status, headers, config) {
                    $scope.status = status;
                });
        }
        else {
        }

        $scope.primero();

    };

    $scope.primero = function () {
        idx = 0;
        $scope.productoActual = $scope.productos[idx];
    };
    

     $scope.ultimo = function () {
        //El ultimo elemento se determina por la longitud del array (lenght)
        idx = $scope.productos.length - 1;
        $scope.productoActual = $scope.productos[idx];
    };
}]);
