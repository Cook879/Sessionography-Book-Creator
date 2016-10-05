
$( document ).ready( function () {

	var $id = $( '#modal-other-id' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/for_other/' + $id, function ( result ) {
			$( '#modal-other-description' ).val( result.description );
		} );
	}

	$( '#modal-other-submit' ).click( function () {

		var description = $( '#modal-other-description' ).val();

		var id = $( '#modal-other-id' ).val();

		var object = { "description": description };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/for_other', 'POST', object, $( '#modal-other-success' ), $( '#modal-other-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/for_other/' + $id, 'PUT', object, $( '#modal-other-success' ), $( '#modal-other-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		$( '#modal-session-other-id' ).val( data.id );
		$( '#modal-session-other' ).val( data.description );
		$( "#modal-other-cancel" ).trigger( "click" );
	}
} );


