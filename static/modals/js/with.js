
$( document ).ready( function () {

	var $id = $( '#modal-with-id' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/with/' + $id, function ( result ) {
			$( '#modal-with-name' ).val( result.name );
		} );
	}

	$( '#modal-with-submit' ).click( function () {

		var name = $( '#modal-with-name' ).val();

		var id = $( '#modal-with-id' ).val();

		var object = { "name": name };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/with', 'POST', object, $( '#modal-with-success' ), $( '#modal-with-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/with/' + $id, 'PUT', object, $( '#modal-with-success' ), $( '#modal-with-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		$( '#modal-session-with-id' ).val( data.id );
		$( '#modal-session-with' ).val( data.name );
		$( "#modal-with-cancel" ).trigger( "click" );
	}
} );


