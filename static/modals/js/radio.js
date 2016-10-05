
$( document ).ready( function () {

	var $id = $( '#modal-radio-id' ).val();
	var $caller = $( '#modal-radio-caller' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/radio/' + $id, function ( result ) {
			$( '#modal-radio-title' ).val( result.title );
			$( '#modal-radio-network' ).val( result.network.name );
			$( '#modal-radio-network-id' ).val( result.network.id );
			$( '#modal-radio-sponsor' ).val( result.sponsor.name );
			$( '#modal-radio-sponsor-id' ).val( result.sponsor.id );
			$( '#modal-radio-year' ).val( result.year );
		} );
	}

	$( '#btn-network-edit' ).click( function () {
		$( "#div-modal-studio" ).load( 'studio.html', function () {
			$( '#modal-studio-id' ).val( $( '#modal-radio-network-id' ).val() );
			$( '#modal-studio-caller' ).val( "radio-network" );
			$.getScript( 'js/studio.js' );
			$( '#studio-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-network-new' ).click( function () {
		$( "#div-modal-studio" ).load( 'studio.html', function () {
			$( '#modal-studio-caller' ).val( "radio-network" );
			$.getScript( 'js/studio.js' );
			$( '#studio-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-sponsor-edit' ).click( function () {
		$( "#div-modal-studio" ).load( 'studio.html', function () {
			$( '#modal-studio-id' ).val( $( '#modal-radio-sponsor-id' ).val() );
			$( '#modal-studio-caller' ).val( "radio-sponsor" );
			$.getScript( 'js/studio.js' );
			$( '#studio-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-sponsor-new' ).click( function () {
		$( "#div-modal-studio" ).load( 'studio.html', function () {
			$( '#modal-studio-caller' ).val( "radio-sponsor" );
			$.getScript( 'js/studio.js' );
			$( '#studio-modal' ).modal( 'show' );
		} );
	} );


	$("#modal-radio-sponsor").on("change paste keyup", function() {
		if($(this).val() == "" ) {
			$("#modal-radio-sponsor-id").val("");
		}
	});

	$( '#modal-radio-submit' ).click( function () {
		var title = $( '#modal-radio-title' ).val();
		var network = $( '#modal-radio-network-id' ).val();
		var sponsor = $( '#modal-radio-sponsor-id' ).val();
		var year = $( '#modal-radio-year' ).val();

		var id = $( '#modal-radio-id' ).val();

		var object = { "title": title, "network": network, "sponsor": sponsor, "year": year };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/radio', 'POST', object, $( '#modal-radio-success' ), $( '#modal-radio-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/radio/' + $id, 'PUT', object, $( '#modal-radio-success' ), $( '#modal-radio-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		if ( $caller == "take" ) {
			$( '#modal-take-radio-id' ).val( data.id );
			$( '#modal-take-radio' ).val( data.title );
		} else {
			$( '#modal-session-radio-id' ).val( data.id );
			$( '#modal-session-radio' ).val( data.title );
		}
		$( "#modal-radio-cancel" ).trigger( "click" );
	}

	$( '#modal-radio-network' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/studio',
		onSelect: function ( suggestion ) {
			$( '#modal-radio-network-id' ).val( suggestion.data );
		}
	} );
	$( '#modal-radio-sponsor' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/studio',
		onSelect: function ( suggestion ) {
			$( '#modal-radio-sponsor-id' ).val( suggestion.data );
		}
	} );
} );