
$( document ).ready( function () {

	var $id = $( '#modal-person-id' ).val();
	var $callerId = $( '#modal-person-caller' ).val();
	var $type = $( '#modal-person-caller-type' ).val();

	if ( $id != "" ) {
		$.getJSON( '/api/person/' + $id, function ( result ) {
			$( '#modal-person-first-name' ).val( result.firstName );
			$( '#modal-person-last-name' ).val( result.lastName );
		} );
	}

	$( '#modal-person-submit' ).click( function () {

		var firstName = $( '#modal-person-first-name' ).val();
		var lastName = $( '#modal-person-last-name' ).val();

		var id = $( '#modal-person-id' ).val();

		var object = { "firstName": firstName, "lastName": lastName };

		if ( id == "" ) {
			object[ 'id' ] = null;
			ajaxRequest( '/api/person', 'POST', object, $( '#modal-person-success' ), $( '#modal-person-error' ), processSubmit );
		} else {
			object[ 'id' ] = $id;
			ajaxRequest( '/api/person/' + $id, 'PUT', object, $( '#modal-person-success' ), $( '#modal-person-error' ), processSubmit );
		}

	} );

	function processSubmit( data ) {
		if ( $type == "song" ) {
			$( '#modal-song-person-person-id-' + $callerId ).val( data.id );
			if ( data.firstName != null )
				$( '#modal-song-person-person-' + $callerId ).val( data.firstName + " " + data.lastName );
			else
				$( '#modal-song-person-person-' + $callerId ).val( data.lastName );
		} else {
			$( '#modal-session-song-person-person-id-' + $callerId ).val( data.id );
			if ( data.firstName != null )
				$( '#modal-session-song-person-person-' + $callerId ).val( data.firstName + " " + data.lastName );
			else
				$( '#modal-session-song-person-person-' + $callerId ).val( data.lastName );

		}
		$( '#modal-person-cancel' ).trigger( "click" );
	}

} );


