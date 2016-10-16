$( document ).ready( function () {

	var $id = $( '#modal-television-id' ).val();
	var $caller = $( '#modal-television-caller' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/television/' + $id, function ( result ) {
			$( '#modal-television-title' ).val( result.title );
			$( '#modal-television-network' ).val( result.network.name );
			$( '#modal-television-network-id' ).val( result.network.id );
			$( '#modal-television-year' ).val( result.year );
		} );
	}

	$( '#btn-network-edit' ).click( function () {
		$( "#div-modal-studio" ).load( 'studio.html', function () {
			$( '#modal-studio-id' ).val( $( '#modal-television-network-id' ).val() );
			$( '#modal-studio-caller' ).val( "television" );
			$.getScript( 'js/studio.js' );
			$( '#studio-modal' ).modal( 'show' );
		} );
	} );
	$( '#btn-network-new' ).click( function () {
		$( "#div-modal-studio" ).load( 'studio.html', function () {
			$( '#modal-studio-caller' ).val( "television" );
			$.getScript( 'js/studio.js' );
			$( '#studio-modal' ).modal( 'show' );
		} );
	} );

	$( '#modal-television-submit' ).click( function () {
		var title = $( '#modal-television-title' ).val();
		var network = $( '#modal-television-network-id' ).val();
		var year = $( '#modal-television-year' ).val();

		var id = $( '#modal-television-id' ).val();

		var object = { "title": title, "network": network, "year": year };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/television', 'POST', object, $( '#modal-television-success' ), $( '#modal-television-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/television/' + $id, 'PUT', object, $( '#modal-television-success' ), $( '#modal-television-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		if ( $caller == "take" ) {
			$( '#modal-take-television-id' ).val( data.id );
			$( '#modal-take-television' ).val( data.title );
		} else {
			$( '#modal-session-television-id' ).val( data.id );
			$( '#modal-session-television' ).val( data.title );
		}
		$( "#modal-television-cancel" ).trigger( "click" );
	}

	$( '#modal-television-network' ).devbridgeAutocomplete( {
		serviceUrl: '/api/autocomplete/studio',
		onSelect: function ( suggestion ) {
			$( '#modal-television-network-id' ).val( suggestion.data );
		}
	} );
} );