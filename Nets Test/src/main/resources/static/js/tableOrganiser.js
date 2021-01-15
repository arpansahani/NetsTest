function listTableOrganizerControl($scope, $filter) {
        // init
        $scope.sortingOrder = 0;
        $scope.reverse = false;
        $scope.filteredItems = [];
        $scope.groupedItems = [];
        $scope.itemsPerPage = 1000;
        $scope.pagedItems = [];
        $scope.currentPage = 0;

        $scope.items = $scope.populationChangeRecords;
        var record = function (_populationRegion) {
            if (_populationRegion) {
                return true;
            }
        };

        $scope.checkIfRecordExists = function () {
            $scope.filteredItems = $filter('filter')($scope.items, function (item) {
                for(var attr in item) {
                    if (record(item[attr]))
                        return true;
                }
                return false;
            });
            // for sorting order
            if ($scope.sortingOrder !== '') {
                $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);
            }
            $scope.currentPage = 0;
            $scope.groupToPages();
        };

        // for pages calculation and pagination - start
        $scope.groupToPages = function () {
            $scope.pagedItems = [];

            for (var i = 0; i < $scope.filteredItems.length; i++) {
                if (i % $scope.itemsPerPage === 0) {
                    $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [ $scope.filteredItems[i] ];
                } else {
                    $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
                }
            }
        };
        $scope.range = function (start, end) {
            var ret = [];
            if (!end) {
                end = start;
                start = 0;
            }
            for (var i = start; i < end; i++) {
                ret.push(i);
            }
            return ret;
        };
        $scope.prevPage = function () {
            if ($scope.currentPage > 0) {
                $scope.currentPage--;
            }
        };
        $scope.nextPage = function () {
            if ($scope.currentPage < $scope.pagedItems.length - 1) {
                $scope.currentPage++;
            }
        };
        $scope.setPage = function () {
            $scope.currentPage = this.n;
        };
        // pagination - ends

        $scope.checkIfRecordExists();

        // for a change in sorting order
        $scope.sort_by = function(newSortingOrder) {
            if ($scope.sortingOrder == newSortingOrder)
                $scope.reverse = !$scope.reverse;

            $scope.sortingOrder = newSortingOrder;

            $('th i').each(function(){
                $(this).removeClass().addClass('icon-sort');
            });
            if ($scope.reverse)
                $('th.'+new_sorting_order+' i').removeClass().addClass('icon-chevron-up');
            else
                $('th.'+new_sorting_order+' i').removeClass().addClass('icon-chevron-down');
        };
    };
    listTableOrganizerControl.$inject = ['$scope', '$filter'];