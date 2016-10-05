
$( document ).ready( function () {

	var $id = $( '#modal-location-id' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/location/' + $id, function ( result ) {
			$( '#modal-location-name' ).val( result.name );
			$( '#modal-location-at' ).prop( 'checked', result.at );
		} );
	}

	$( '#modal-location-submit' ).click( function () {

		var name = $( '#modal-location-name' ).val();
		var at = $( '#modal-location-arranger' ).prop( 'checked' );

		var id = $( '#modal-location-id' ).val();

		var object = { "name": name, "at": at };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/location', 'POST', object, $( '#modal-location-success' ), $( '#modal-location-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/location/' + $id, 'PUT', object, $( '#modal-location-success' ), $( '#modal-location-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		$( '#modal-session-location-id' ).val( data.id );
		$( '#modal-session-location' ).val( data.name );
		$( "#modal-location-cancel" ).trigger( "click" );
	}


} );


