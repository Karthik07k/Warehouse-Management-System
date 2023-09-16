$(document).ready(
	function() {
		// 1. Hide Error Variable

		$("#orderModeError").hide();
		$("#orderCodeError").hide();
		$("#orderTypeError").hide();
		$("#orderAcceptError").hide();
		$("#orderDescError").hide();

		// 2. Define Error Variable

		var orderModeError = false;
		var orderCodeError = false;
		var orderTypeError = false;
		var orderAcceptError = false;
		var orderDescError = false;

		// 3. Define Validate Function

		function validate_orderMode() {
			var length = $('[name="orderMode"]:checked').length;
			if (length == 0) {
				$("#orderModeError").show();
				$("#orderModeError").html("<b>*Choose Anyone</b>");
				$("#orderModeError").css('color', 'red');
				orderModeError = false;
			} else {
				$("#orderModeError").hide();
				orderModeError = true;
			}
			return orderModeError;
		}

		function validate_orderCode() {
			var val = $("#orderCode").val();
			var exp = /^[A-Z\-\s]{4,12}$/;
			if (val == '') {
				$("#orderCodeError").show();
				$("#orderCodeError").html("<b>*Enter Code</b>");
				$("#orderCodeError").css('color', 'red');
				orderCodeError = false;
			}

			else if (!exp.test(val)) {
				$("#orderCodeError").show();
				$("#orderCodeError").html("<b>*Only 4-12 chars allowed</b>");
				$("#orderCodeError").css('color', 'red');
				orderCodeError = false;
			}

			else {
				var id = 0; //for register
				if ($("#id").val() !== undefined) {
					id = $("#id").val(); //edit
				}
				$.ajax({
					url: 'validate',
					data: { "code": val, "id": id },
					success: function(resTxt) {
						if (resTxt != "") { //duplicate exist
							$("#orderCodeError").show();
							$("#orderCodeError").html(resTxt);
							$("#orderCodeError").css('color', 'red');
							orderCodeError = false;
						} else {
							$("#orderCodeError").hide();
							orderCodeError = true;
						}
					}
				});
			}
			return orderCodeError;
		}

		function validate_orderType() {
			var val = $("#orderType").val();
			if (val == '') {
				$("#orderTypeError").show();
				$("#orderTypeError").html(
					"<b>*Select Order Type</b>");
				$("#orderTypeError").css('color', 'red');
				orderTypeError = false;
			} else {
				$("#orderTypeError").hide();
				orderTypeError = true;
			}
			return orderTypeError;
		}

		function validate_orderAccept() {
			var length = $('[name="orderAccept"]:checked').length;
			if (length == 0) {
				$("#orderAcceptError").show();
				$("#orderAcceptError").html(
					"<b>*Select Accept Order</b>");
				$("#orderAcceptError").css('color', 'red');
				orderAcceptError = false;
			} else {
				$("#orderAcceptError").hide();
				orderAcceptError = true;
			}
			return orderAcceptError;
		}

		function validate_orderDesc() {
			var val = $("#orderDesc").val();
			if (val == '') {
				$("#orderDescError").show();
				$("#orderDescError").html(
					"<b>*Enter Description</b>");
				$("#orderDescError").css('color', 'red');
				orderDescError = false;
			} else {
				$("#orderDescError").hide();
				orderDescError = true;
			}
			return orderDescError;
		}
		// 4. Link Action Event

		$('[name="orderMode"]').change(function() {
			validate_orderMode();
		});

		$("#orderCode").keyup(function() {
			$(this).val($(this).val().toUpperCase());
			validate_orderCode();
		});

		$("#orderType").change(function() {
			validate_orderType();
		});

		$('[name="orderAccept"]').click(function() {
			validate_orderAccept();
		});

		$("#orderDesc").keyup(function() {
			validate_orderDesc();
		});
		// 5. On Click Submit 

		$("#myOrderMethod").submit(
			function() {
				validate_orderMode();
				validate_orderCode();
				validate_orderType();
				validate_orderAccept();
				validate_orderDesc();

				if (orderModeError && orderCodeError
					&& orderTypeError && orderAcceptError
					&& orderDescError)
					return true;
				else
					return false;
			})
	});