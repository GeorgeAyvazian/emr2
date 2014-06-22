var calendar = $('#calendar').calendar(
    {
        tmpl_path: '/assets/tmpls/',
        events_source: function () {
            return [];
        }
    }
);
var myViewModel = {
    'firstName': ko.observable('x'),
    'errorCheck': ko.observable('form-group'),
    'searchAll': function (data, event) {
        appRoutes.controllers.Application.findAllPatients(event.key).ajax({ 'success': function (data) {
            alert(data)
        } })
    },
//    'theTemplate': ko.observable('patient-center-template'),
//    'fetchTemplate': function (data, event) {
//        var templateToLoad = event.currentTarget.id;
//        this.theTemplate('addPatient' === templateToLoad ? 'patient-center-template' : 'email-center-template');
//    },
    'highlight': function(data, event) {
        var val = event.currentTarget.value;
        if(!val || val.length < 1) window.console.log(this.errorCheck(this.errorCheck() + " has-error"));
    }
    //,
//    'fetchSchedules': function () {
//        $.getJSON('@routes.Schedules.view()',
//            function (data) {
//                myViewModel.firstName(data[0].provider.firstName);
//            })
//    }
}
ko.applyBindings(myViewModel);