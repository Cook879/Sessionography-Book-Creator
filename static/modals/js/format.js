
$( document ).ready( function () {

	var $id = $( '#modal-format-id' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/format/' + $id, function ( result ) {
			$( '#modal-format-name' ).val( result.name );
		} );
	}

	$( '#modal-format-submit' ).click( function () {

		var name = $( '#modal-format-name' ).val();

		var id = $( '#modal-format-id' ).val();

		var object = { "name": name };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/format', 'POST', object, $( '#modal-format-success' ), $( '#modal-format-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/format/' + $id, 'PUT', object, $( '#modal-format-success' ), $( '#modal-format-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		getFormats( data.id );
		$( '#modal-release-format' ).val( data.id );
		$( "#modal-format-cancel" ).trigger( "click" );
	}
} );


