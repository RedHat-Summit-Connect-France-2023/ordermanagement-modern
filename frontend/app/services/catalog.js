'use strict';

angular.module("app")

.factory('catalog', ['$http', '$q', 'COOLSTORE_CONFIG', 'Auth', '$location', function($http, $q, COOLSTORE_CONFIG, $auth, $location) {
	var factory = {}, products, baseUrl;

	console.log("PRODUCT ",products)

    if(!!COOLSTORE_CONFIG.MONOLITH) {

	} else {
		if ($location.protocol() === 'https') {
			baseUrl = (COOLSTORE_CONFIG.SECURE_API_ENDPOINT.startsWith("https://") ? COOLSTORE_CONFIG.SECURE_API_ENDPOINT : "https://" + COOLSTORE_CONFIG.SECURE_API_ENDPOINT );
		} else {
			baseUrl = (COOLSTORE_CONFIG.CATALOG_API_ENDPOINT.startsWith("http://") ? COOLSTORE_CONFIG.API_ENDPOINT : "http://" + COOLSTORE_CONFIG.CATALOG_API_ENDPOINT );
		}
	}
	console.log("CATALOG 2 : URL ",baseUrl);

	factory.getProducts = function() {

		console.log ("get product called !")
		var deferred = $q.defer();
        if (products) {
            deferred.resolve(products);
        } else {
			console.log ("get product called 1 !")

            $http({
                method: 'GET',
				url: baseUrl + '/products'
            }).then(function(resp) {
				console.log ("get product result ",resp)

                products = resp.data;
                deferred.resolve(resp.data);
            }, function(err) {
				console.log(" product error ",err)
                deferred.reject(err);
            });
        }
	   return deferred.promise;
	};

	return factory;
}]);
