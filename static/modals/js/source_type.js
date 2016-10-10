$( document ).ready( function () {

	var $id = $( '#modal-source-type-id' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/source_type/' + $id, function ( result ) {
			$( '#modal-source-type-name' ).val( result.name );
			$( '#modal-source-type-from-the' ).prop( 'checked', result.fromThe );
		} );
	}

	$( '#modal-source-type-submit' ).click( function () {

		var name = $( '#modal-source-type-name' ).val();
		var fromThe = $( '#modal-source-type-from-the' ).prop( 'checked' );

		var id = $( '#modal-source-type-id' ).val();

		var object = { "name": name, "fromThe": fromThe };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/source_type', 'POST', object, $( '#modal-source-type-success' ), $( '#modal-source-type-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/source_type/' + $id, 'PUT', object, $( '#modal-source-type-success' ), $( '#modal-source-type-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		getSourceTypes( data.id );
		$( '#modal-source-source-type' ).val( data.id );
		$( "#modal-source-type-cancel" ).trigger( "click" );
	}
} );


