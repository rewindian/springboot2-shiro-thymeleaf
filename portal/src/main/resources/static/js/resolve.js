define(["require", "promise"], function(require, Promise){
    var resolve = function(dep) {
        return function() {
            if (!(dep instanceof Array)) {
                dep = [dep];
            }
            var deferred = Promise.defer();
            require(dep, function(res) {
                deferred.resolve(res);
            });
            return deferred.promise;
        };
    };
    return resolve;
});