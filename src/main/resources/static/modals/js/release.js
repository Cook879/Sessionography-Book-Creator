$( document ).ready( function () {

	var $id = $( '#modal-release-id' ).val();
	var $caller = $( '#modal-release-caller' ).val();

	getFormats();

	if ( $id != "" ) {
		$.getJSON( '/api/release/' + $id, function ( result ) {
			$( '#modal-release-title' ).val( result.title );
			if ( result.label != null ) {
				$( '#modal-release-label-id' ).val( result.label.id );
				$( '#modal-release-label' ).val( result.label.name );
			}

			$( '#modal-release-format' ).val( result.format );

			$( '#modal-release-year' ).val( result.year );
			$( '#modal-release-all' ).prop( 'checked', result.all );

		} );
	}

	$( '#btn-release-label-edit' ).click( function () {
		$( "#div-modal-label-release" ).load( 'label.html', function () {
			$( '#modal-label-id' ).val( $( '#modal-release-label-id' ).val() );
			$( '#modal-label-caller' ).val( "release" );
			$.getScript( 'js/label.js' );
			$( '#label-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-release-label-new' ).click( function () {
		$( "#div-modal-label-release" ).load( 'label.html', function () {
			$( '#modal-label-caller' ).val( "release" );
			$.getScript( 'js/label.js' );
			$( '#label-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-release-format-edit' ).click( function () {
		$( "#div-modal-format" ).load( 'format.html', function () {
			$( '#modal-format-id' ).val( $( '#modal-release-format' ).val() );
			$.getScript( 'js/format.js' );
			$( '#format-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-release-format-new' ).click( function () {
		$( "#div-modal-format" ).load( 'format.html', function () {
			$.getScript( 'js/format.js' );
			$( '#format-modal' ).modal( 'show' );
		} );
	} );

	$( '#modal-release-submit' ).click( function () {
		var title = $( '#modal-release-title' ).val();
		var label = $( '#modal-release-label-id' ).val();
		var year = $( '#modal-release-year' ).val();
		var format = $( '#modal-release-format' ).val();
		var all = $( '#modal-release-all' ).prop( 'checked' );

		var id = $( '#modal-release-id' ).val();

		var object = { "title": title, "label": label, "year": year, "format": format, "all": all };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/release', 'POST', object, $( '#modal-release-success' ), $( '#modal-release-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/release/' + $id, 'PUT', object, $( '#modal-release-success' ), $( '#modal-release-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		if ( $caller == "original" ) {
			$( '#modal-take-original-release-id' ).val( data.id );
			$( '#modal-take-original-release' ).val( data.title );
		} else {
			$( '#modal-take-key-release-id' ).val( data.id );
			$( '#modal-take-key-release' ).val( data.title );
		}
		$( "#modal-release-cancel" ).trigger( "click" );
	}

	$( '#modal-release-label' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/label',
		onSelect: function ( suggestion ) {
			$( '#modal-release-label-id' ).val( suggestion.data );
		}
	} );


	$( "#modal-release-label" ).on( "change paste keyup", function () {
		if ( $( this ).val() == "" ) {
			$( "#modal-release-label-id" ).val( "" );
		}
	} );
} );

function getFormats( providedVal ) {
	var select = $( '#modal-release-format' );
	var selectVal = select.val();

	select.find( 'option' ).remove();

	$.getJSON( '/api/format', function ( result ) {
		$.each( result, function ( id, item ) {
			select.append( '<option value="' + item.id + '">' + item.name + '</option>' );
		} );

		select.val( selectVal );

		if ( providedVal != null ) {
			select.val( providedVal );
		}
	} );
}