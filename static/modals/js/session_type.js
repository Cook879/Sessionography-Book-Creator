
$( document ).ready( function () {

	var $id = $( '#modal-session-type-id' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/session_type/' + $id, function ( result ) {
			$( '#modal-session-type-name' ).val( result.name );
		} );
	}

	$( '#modal-session-type-submit' ).click( function () {

		var name = $( '#modal-session-type-name' ).val();

		var id = $( '#modal-session-type-id' ).val();

		var object = { "name": name };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/session_type', 'POST', object, $( '#modal-session-type-success' ), $( '#modal-session-type-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/session_type/' + $id, 'PUT', object, $( '#modal-session-type-success' ), $( '#modal-session-type-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		getSessionTypes( data.id );
		$( '#modal-session-session-type' ).val( data.id );
		$( "#modal-session-type-cancel" ).trigger( "click" );
	}
} );


