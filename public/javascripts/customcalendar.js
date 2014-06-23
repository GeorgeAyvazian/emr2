var falsey = [undefined, null, "NaN", 0, "", false];
Array.prototype.contains = function (val) {
    var idx = 0, len = this.length;
    for (; idx < len; idx++) {
        if (this[idx] === val.toString() || this[idx] === val || this[idx] == val.valueOf()) {
            return true;
        }
    }
    return false;
}

var _isTruthy = function (obj) {
    return obj !== undefined && obj !== null && !falsey.contains(obj)
}
var _isFalsey = function (obj) {
    return !_isTruthy(obj);
}

var InvalidArgumentError = function (msg) {
    this.message = msg;
};
InvalidArgumentError.prototype = new Error();

var _Event = function (dateRange, description) {
    var _dateRange;
    var _description;
    if (_isFalsey(dateRange) || _isFalsey(description)) {
        throw new InvalidArgumentError("invalid parameters")
    }
    _dateRange = dateRange;
    _description = description;

    this.getDateRange = function () {
        return _dateRange;
    }
    this.overlapsWith = function (event) {
        if (_date == event.getDate()) {
            return true;
        }
    }
};

var _DateRange = function (date1) {
    var _d1, _d2 = null, s = false;
    var isD1Invalid = _isFalsey(date1);
    var secondDateProvided = arguments.length > 1;
    var isD2Invalid = secondDateProvided && _isFalsey(arguments[1]);
    if (isD1Invalid || isD2Invalid) {
        throw new InvalidArgumentError("Invalid date(s): " + (isD1Invalid ? date1 : "") + (isD1Invalid && isD2Invalid ? " , " : "") + (isD2Invalid ? arguments[1] : ""));
    }
    _d1 = Date.parse(date1);
    if (isNaN(_d1) || _d1 < 0) {
        throw new InvalidArgumentError();
    }
    if (secondDateProvided) {
        _d2 = Date.parse(arguments[1]);
        if (isNaN(_d2) || _d2 < 0 || _d2 < _d1) {
            throw new InvalidArgumentError();
        }
    }
    s = _d1 === _d2;

    function isInRange(date) {
        var d = date instanceof Date ? Date.parse(date) : date;
        if (_d2 === null) {
            return d === _d1;
        }
        return !!(d >= _d1 && d <= _d2);
    }

    return {
        'date1': _d1,
        'date2': _d2 === null ? _d1 : _d2,
        'isSingleDay': s,
        'difference': s ? 0 : _d2 - _d1,
        'isInRnage': isInRange,
        'overlaps': function (dateRange) {
            return isInRange(dateRange.date1) || isInRange(dateRange.date2);
        }
    }
};