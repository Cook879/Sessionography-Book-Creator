$( document ).ready( function () {

	var $id = $( '#modal-label-id' ).val();
	var $caller = $( '#modal-label-caller' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/label/' + $id, function ( result ) {
			$( '#modal-label-name' ).val( result.name );
		} );
	}

	$( '#modal-label-submit' ).click( function () {

		var name = $( '#modal-label-name' ).val();

		var id = $( '#modal-label-id' ).val();

		var object = { "name": name };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/label', 'POST', object, $( '#modal-label-success' ), $( '#modal-label-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/label/' + $id, 'PUT', object, $( '#modal-label-success' ), $( '#modal-label-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		if ( $caller == "album" ) {
			$( '#modal-album-label-id' ).val( data.id );
			$( '#modal-album-label' ).val( data.name );
		} else if ( $caller == "release" ) {
			$( '#modal-release-label-id' ).val( data.id );
			$( '#modal-release-label' ).val( data.name );
		} else {
			$( '#modal-session-label-id' ).val( data.id );
			$( '#modal-session-label' ).val( data.name );
		}

		$( "#modal-label-cancel" ).trigger( "click" );
	}
} );


