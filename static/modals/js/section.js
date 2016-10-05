
$( document ).ready( function () {

	var $id = $( '#modal-section-id' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/section/' + $id, function ( result ) {
			$( '#modal-section-title' ).val( result.title );
			$( '#modal-section-description' ).val( result.description );
			$( '#modal-section-position' ).val( result.position );
		} );
	}

	$( '#modal-section-submit' ).click( function () {

		var title = $( '#modal-section-title' ).val();
		var description = $( '#modal-section-description' ).val();
		var position = $( '#modal-section-position' ).val();

		var id = $( '#modal-section-id' ).val();

		var object = { "title": title, "position": position, "description": description };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/section', 'POST', object, $( '#modal-section-success' ), $( '#modal-section-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/section/' + $id, 'PUT', object, $( '#modal-section-success' ), $( '#modal-section-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		getSections( data.id );
		$( '#modal-session-section' ).val( data.id );
		$( "#modal-section-cancel" ).trigger( "click" );
	}
} );


