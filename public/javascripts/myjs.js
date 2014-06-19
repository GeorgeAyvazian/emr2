$('#dp3').datepicker();
var myViewModel = {
    'firstName': ko.observable('x'),
    'searchAll': function (data, event) {
        appRoutes.controllers.Application.findAllPatients(event.key).ajax({ 'success': function (data) {
            alert(data)
        } })
    },
    'theTemplate': ko.observable('patient-center-template'),
    'fetchTemplate': function (data, event) {
        var templateToLoad = event.currentTarget.id;
        this.theTemplate('addPatient' === templateToLoad ? 'patient-center-template' : 'email-center-template');
    }//,
//    'fetchSchedules': function () {
//        $.getJSON('@routes.Schedules.view()',
//            function (data) {
//                myViewModel.firstName(data[0].provider.firstName);
//            })
//    }
}
ko.applyBindings(myViewModel);