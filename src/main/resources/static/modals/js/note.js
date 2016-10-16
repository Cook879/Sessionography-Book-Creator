$( document ).ready( function () {

	var $id = $( '#modal-note-id' ).val();
	var $callerId = $( '#modal-note-caller' ).val();
	var $session = $( '#modal-note-session' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/person_role_note/' + $id, function ( result ) {
			$( '#modal-note-note' ).val( result.note );
		} );
	}

	$( '#modal-note-submit' ).click( function () {

		var note = $( '#modal-note-note' ).val();

		var id = $( '#modal-note-id' ).val();

		var object = { "note": note, "session": $session };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/person_role_note', 'POST', object, $( '#modal-note-success' ), $( '#modal-note-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/person_role_note/' + $id, 'PUT', object, $( '#modal-note-success' ), $( '#modal-note-error' ), processSubmit );
		}

	} );

	function processSubmit( data ) {
		getNotes( $callerId, data.id );
		$( '#modal-session-song-person-note-' + $callerId ).val( data.id );
		$( '#modal-note-cancel' ).trigger( "click" );
	}

} );


