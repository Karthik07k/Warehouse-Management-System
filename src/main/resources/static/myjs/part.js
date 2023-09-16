$(document)
	.ready(
		function() {
			// 1. hide Error variable
			$("#partCodeError").hide();
			$("#partDimsError").hide();
			$("#partBaseCostError").hide();
			$("#partCurrencyError").hide();
			$("#uomError").hide();
			$("#partDescError").hide();

			// 2. define Error variable
			var partCodeError = false;
			var partDimsError = false;
			var partBaseCostError = false;
			var partCurrencyError = false;
			var uomError = false;
			var partDescError = false;

			// 3. define validate function
			function validate_partCode() {
				var val = $("#partCode").val();
				var exp = /^[A-Z\-\s]{8,15}$/;
				if (val == '') {
					$("#partCodeError").show();
					$("#partCodeError").html(
						"<b>*Enter code</b>");
					$("#partCodeError").css('color', 'red');
					partCodeError = false;
				}

				else if (!exp.test(val)) {
					$("#partCodeError").show();
					$("#partCodeError")
						.html(
							"<b>*Code must be 8-15 uppercase letters</b>");
					$("#partCodeError").css('color', 'red');
					partCodeError = false;
				}

				else {
					var id = 0; //for register
					if ($("#id").val() != undefined) { //for edit
						id = $("#id").val(); //if present
						partCodeError = true;
					}
					//ajax call start    
					$.ajax({
						url: 'validate',
						data: {
							"code": val,
							"id": id
						},
						success: function(resTxt) {
							if (resTxt != '') { //error, duplicate exist
								$("#partCodeError").show();
								$("#partCodeError")
									.html(resTxt);
								$("#partCodeError").css(
									'color', 'red');
								partCodeError = false;
							} else { //valid, no duplicate
								$("#partCodeError").hide();
								partCodeError = true;
							}
						}
					});
					//ajax call end    
				}

				return partCodeError;
			}

			function validate_partDims() {
				var val1 = $("#partWid").val();
				var val2 = $("#partLen").val();
				var val3 = $("#partHt").val();
				var exp = /^[0-9\.]{1,5}$/;

				if (val1 == '' || val2 == '' || val3 == '') {
					$("#partDimsError").show();
					$("#partDimsError").html(
						"<b>*Enter Dims Value</b>");
					$("#partDimsError").css('color', 'red');
					partDimsError = false;
				}

				else if (val1 <= 0 || val2 <= 0 || val3 <= 0) {
					$("#partDimsError").show();
					$("#partDimsError").html(
						" <b>*Dimensions must be > 0 </b>");
					$("#partDimsError").css('color', 'red');
				}

				else if (!exp.test(val1) || !exp.test(val2)
					|| !exp.test(val3)) {
					$("#partDimsError").show();
					$("#partDimsError").html(
						"<b>*Invalid Dimensions</b");
					$("#partDimsError").css('color', 'red');
					partDimsError = false;
				}

				else {
					$("#partDimsError").hide();
					partDimsError = true;
				}
				return partDimsError;
			}

			function validate_partBaseCost() {
				var val = $("#partBaseCost").val();
				var exp = /^[0-9\.]{1,8}$/

				if (val == '') {
					$("#partBaseCostError").show();
					$("#partBaseCostError").html(
						"<b>*Enter cost</b>");
					$("#partBaseCostError").css('color', 'red');
					partBaseCostError = false;
				}

				else if (val <= 0) {
					$("#partBaseCostError").show();
					$("#partBaseCostError").html(
						"<b>* Cost must be > 0 </b>");
					$("#partBaseCostError").css('color', 'red');
				} else if (!exp.test(val)) {
					$("#partBaseCostError").show();
					$("#partBaseCostError").html(
						"*<b> Invalid Cost</b");
					$("#partBaseCostError").css('color', 'red');
					partBaseCostError = false;
				}

				else {
					$("#partBaseCostError").hide();
					partBaseCostError = true;
				}
				return partBaseCostError;
			}

			function validate_partCurrency() {
				var val = $("#partCurrency").val();
				if (val == '') {
					$("#partCurrencyError").show();
					$("#partCurrencyError").html(
						"<b>*Select Currency</b>");
					$("#partCurrencyError").css('color', 'red');
					partCurrencyError = false;
				} else {
					$("#partCurrencyError").hide();
					partCurrencyError = true;
				}
				return partCurrencyError;
			}

			function validate_uomId() {
				var val = $("#uomId").val();
				if (val == '') {
					$("#uomError").show();
					$("#uomError").html("<b>*Select Uom</b>");
					$("#uomError").css('color', 'red');
					uomError = false;
				} else {
					$("#uomError").hide();
					uomError = true;
				}
				return uomError;
			}

			function validate_partDesc() {
				var val = $("#partDesc").val();
				if (val == '') {
					$("#partDescError").show();
					$("#partDescError").html(
						"<b>*Enter desc</b>");
					$("#partDescError").css('color', 'red');
					partDescError = false;
				} else {
					$("#partDescError").hide();
					partDescError = true;
				}
				return partDescError;
			}
			// 4. link action event
			$("#partCode").keyup(function() {
				$(this).val($(this).val().toUpperCase());
				validate_partCode();
			})

			$("#partWid").keyup(function() {
				validate_partDims();
			})

			$("#partLen").keyup(function() {
				validate_partDims();
			})

			$("#partHt").keyup(function() {
				validate_partDims();
			})

			$("#partBaseCost").keyup(function() {
				validate_partBaseCost();
			})

			$("#partCurrency").change(function() {
				validate_partCurrency();
			})

			$("#uomId").change(function() {
				validate_uomId();
			})

			$("#partDesc").keyup(function() {
				validate_partDesc();
			})

			// 5. onclick submit
			$("#myPartForm").submit(
				function() {
					validate_partCode();
					validate_partDims();
					validate_partBaseCost();
					validate_partCurrency();
					validate_uomId();
					validate_partDesc();

					if (partCodeError && partDimsError
						&& partBaseCostError
						&& partCurrencyError
						&& uomError && partDescError)

						return true;
					else
						return false;
				})
		})