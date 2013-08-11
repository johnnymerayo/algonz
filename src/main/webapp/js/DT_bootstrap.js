/* Default class modification */
$.extend($.fn.dataTableExt.oStdClasses, {
	"sSortAsc" : "header headerSortDown",
	"sSortDesc" : "header headerSortUp",
	"sSortable" : "header"
});

/* API method to get paging information */
$.fn.dataTableExt.oApi.fnPagingInfo = function(oSettings) {
	return {
		"iStart" : oSettings._iDisplayStart,
		"iEnd" : oSettings.fnDisplayEnd(),
		"iLength" : oSettings._iDisplayLength,
		"iTotal" : oSettings.fnRecordsTotal(),
		"iFilteredTotal" : oSettings.fnRecordsDisplay(),
		"iPage" : Math.ceil(oSettings._iDisplayStart
				/ oSettings._iDisplayLength),
		"iTotalPages" : Math.ceil(oSettings.fnRecordsDisplay()
				/ oSettings._iDisplayLength)
	};
}

/* Bootstrap style pagination control */
$
		.extend(
				$.fn.dataTableExt.oPagination,
				{
					"bootstrap" : {
						"fnInit" : function(oSettings, nPaging, fnDraw) {
							var oLang = oSettings.oLanguage.oPaginate;
							var fnClickHandler = function(e) {
								e.preventDefault();
								if (oSettings.oApi._fnPageChange(oSettings,
										e.data.action)) {
									fnDraw(oSettings);
								}
							};

							$(nPaging)
									.addClass('pagination')
									.append(
											'<ul class="pagination">'
													+ '<li class="prev disabled"><a href="#">&larr; '
													+ oLang.sPrevious
													+ '</a></li>'
													+ '<li class="next disabled"><a href="#">'
													+ oLang.sNext
													+ ' &rarr; </a></li>'
													+ '</ul>');
							var els = $('a', nPaging);
							$(els[0]).bind('click.DT', {
								action : "previous"
							}, fnClickHandler);
							$(els[1]).bind('click.DT', {
								action : "next"
							}, fnClickHandler);
						},

						"fnUpdate" : function(oSettings, fnDraw) {
							var iListLength = 5;
							var oPaging = oSettings.oInstance.fnPagingInfo();
							var an = oSettings.aanFeatures.p;
							var i, j, sClass, iStart, iEnd, iHalf = Math
									.floor(iListLength / 2);

							if (oPaging.iTotalPages < iListLength) {
								iStart = 1;
								iEnd = oPaging.iTotalPages;
							} else if (oPaging.iPage <= iHalf) {
								iStart = 1;
								iEnd = iListLength;
							} else if (oPaging.iPage >= (oPaging.iTotalPages - iHalf)) {
								iStart = oPaging.iTotalPages - iListLength + 1;
								iEnd = oPaging.iTotalPages;
							} else {
								iStart = oPaging.iPage - iHalf + 1;
								iEnd = iStart + iListLength - 1;
							}

							for (i = 0, iLen = an.length; i < iLen; i++) {
								// Remove the middle elements
								$('li:gt(0)', an[i]).filter(':not(:last)')
										.remove();

								// Add the new list items and their event
								// handlers
								for (j = iStart; j <= iEnd; j++) {
									sClass = (j == oPaging.iPage + 1) ? 'class="active"'
											: '';
									$(
											'<li ' + sClass + '><a href="#">'
													+ j + '</a></li>')
											.insertBefore(
													$('li:last', an[i])[0])
											.bind(
													'click',
													function(e) {
														e.preventDefault();
														oSettings._iDisplayStart = (parseInt(
																$('a', this)
																		.text(),
																10) - 1)
																* oPaging.iLength;
														fnDraw(oSettings);
													});
								}

								// Add / remove disabled classes from the static
								// elements
								if (oPaging.iPage === 0) {
									$('li:first', an[i]).addClass('disabled');
								} else {
									$('li:first', an[i])
											.removeClass('disabled');
								}

								if (oPaging.iPage === oPaging.iTotalPages - 1
										|| oPaging.iTotalPages === 0) {
									$('li:last', an[i]).addClass('disabled');
								} else {
									$('li:last', an[i]).removeClass('disabled');
								}
							}
						}
					}
				});

$.extend($.fn.dataTableExt.oStdClasses, {
	"sWrapper" : "dataTables_wrapper form-inline"
});

/* Table initialisation */
$(document)
.ready(
		function() {
			$('#tablaPaginada')
					.dataTable(
							{
								"sDom": "<'row'<'col-6'l><'col-6'f>r>t<'row'<'col-6'i><'col-6'p>>",
								"sPaginationType" : "bootstrap",
								"oLanguage" : {											
									"sLengthMenu" : "Mostrando _MENU_ registros por página",
									"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
									"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
									"sZeroRecords": "No hay registros coincidentes",
									"sSearch" : "Filtrar resultados:",
									"sInfoEmpty": "0 registros encontrados",
									"oPaginate" : {
										"sNext" : " Siguiente ",
										"sPrevious" : " Anterior "
									}
								},
								"bAutoWidth" : false,
								"bLengthChange" : false,
								"bInfo" : true,
								"bPaginate" : true,
								"bFilter" : true,
								"bSort" : true,
								"iDisplayLength" : 10,
								"aaSorting": [ ]
							});
			
				$('#tablaPaginada2')
						.dataTable(
								{
    								"sDom": "<'row'<'col-6'l><'col-6'f>r>t<'row'<'col-6'i><'col-6'p>>",
									"sPaginationType" : "bootstrap",
									"oLanguage" : {											
										"sLengthMenu" : "Mostrando _MENU_ registros por página",
										"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
										"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
										"sZeroRecords": "No hay registros coincidentes",
										"sSearch" : "Filtrar resultados:",
										"sInfoEmpty": "0 registros encontrados",
										"oPaginate" : {
											"sNext" : " Siguiente ",
											"sPrevious" : " Anterior "
										}
									},
									"bAutoWidth" : false,
									"bLengthChange" : false,
									"bInfo" : true,
									"bPaginate" : true,
									"bFilter" : true,
									"bSort" : true,
									"iDisplayLength" : 10,
									"aaSorting": [ ]
								});
				

					$('#tablaPaginada3')
							.dataTable(
									{
	    								"sDom": "<'row'<'col-6'l><'col-6'f>r>t<'row'<'col-6'i><'col-6'p>>",
										"sPaginationType" : "bootstrap",
										"oLanguage" : {											
											"sLengthMenu" : "Mostrando _MENU_ registros por página",
											"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
											"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
											"sZeroRecords": "No hay registros coincidentes",
											"sSearch" : "Filtrar resultados:",
											"sInfoEmpty": "0 registros encontrados",
											"oPaginate" : {
												"sNext" : " Siguiente ",
												"sPrevious" : " Anterior "
											}
										},
										"bAutoWidth" : false,
										"bLengthChange" : false,
										"bInfo" : true,
										"bPaginate" : true,
										"bFilter" : true,
										"bSort" : true,
										"iDisplayLength" : 10,
										"aaSorting": [ ]
									});


						$('#tablaPaginada4')
								.dataTable(
										{
		    								"sDom": "<'row'<'col-6'l><'col-6'f>r>t<'row'<'col-6'i><'col-6'p>>",
											"sPaginationType" : "bootstrap",
											"oLanguage" : {											
												"sLengthMenu" : "Mostrando _MENU_ registros por página",
												"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
												"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
												"sZeroRecords": "No hay registros coincidentes",
												"sSearch" : "Filtrar resultados:",
												"sInfoEmpty": "0 registros encontrados",
												"oPaginate" : {
													"sNext" : " Siguiente ",
													"sPrevious" : " Anterior "
												}
											},
											"bAutoWidth" : false,
											"bLengthChange" : false,
											"bInfo" : true,
											"bPaginate" : true,
											"bFilter" : true,
											"bSort" : true,
											"iDisplayLength" : 10,
											"aaSorting": [ ]
										});
						

						$('#tablaPaginada_print')
								.dataTable(
										{
											"sDom": "<'row'<'col-6'T><'col-6'f>r>t<'row'<'col-6'i><'col-6'p>>",
											"sPaginationType" : "bootstrap",
											"oLanguage" : {											
												"sLengthMenu" : "Mostrando _MENU_ registros por página",
												"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
												"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
												"sZeroRecords": "No hay registros coincidentes",
												"sSearch" : "Filtrar resultados:",
												"sInfoEmpty": "0 registros encontrados",
												"oPaginate" : {
													"sNext" : " Siguiente ",
													"sPrevious" : " Anterior "
												}
											},
											"bAutoWidth" : false,
											"bLengthChange" : false,
											"bInfo" : true,
											"bPaginate" : true,
											"bFilter" : true,
											"bSort" : true,
											"iDisplayLength" : 10,
											"aaSorting": [ ],
									        "oTableTools": {
									            "aButtons": [{
									                    "sExtends":    "print",
									                    "sButtonText": "Imprimir"
									                }]
									        }
										});
						
						$('#tablaPaginada2_print')
						.dataTable(
								{
									"sDom": "<'row'<'col-6'T><'col-6'f>r>t<'row'<'col-6'i><'col-6'p>>",
									"sPaginationType" : "bootstrap",
									"oLanguage" : {											
										"sLengthMenu" : "Mostrando _MENU_ registros por página",
										"sInfo" : "_TOTAL_ registros encontrados, Mostrando del _START_ al _END_",
										"sInfoFiltered" : "(Filtrados de un total de _MAX_ registros)",
										"sZeroRecords": "No hay registros coincidentes",
										"sSearch" : "Filtrar resultados:",
										"sInfoEmpty": "0 registros encontrados",
										"oPaginate" : {
											"sNext" : " Siguiente ",
											"sPrevious" : " Anterior "
										}
									},
									"bAutoWidth" : false,
									"bLengthChange" : false,
									"bInfo" : true,
									"bPaginate" : true,
									"bFilter" : true,
									"bSort" : true,
									"iDisplayLength" : 10,
									"aaSorting": [ ],
							        "oTableTools": {
							            "aButtons": [{
							                    "sExtends":    "print",
							                    "sButtonText": "Imprimir"
							                }]
							        }
								});
		});





/* Corrige el formato del search con Bootstrap 3 */
$(function(){
    $('.table').each(function(){
        var datatable = $(this);
        // SEARCH - Add the placeholder for Search and Turn this into in-line formcontrol
        var search_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] input');
        search_input.attr('placeholder', 'Filtrar')
        search_input.addClass('form-control input-small')
        search_input.css('width', '250px')
 
        // SEARCH CLEAR - Use an Icon
        var clear_input = datatable.closest('.dataTables_wrapper').find('div[id$=_filter] a');
        clear_input.html('<i class="icon-remove-circle icon-large"></i>')
        clear_input.css('margin-left', '5px')
 
        // LENGTH - Inline-Form control
        var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_length] select');
        length_sel.addClass('form-control input-small')
        length_sel.css('width', '75px')
 
        // LENGTH - Info adjust location
        var length_sel = datatable.closest('.dataTables_wrapper').find('div[id$=_info]');
        length_sel.css('margin-top', '18px')
    });
});