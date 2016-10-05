
$( document ).ready( function () {

	// i is the number of people so far
	var i = 1;

	var $id = $( '#modal-session-song-take-session-song-id' ).val();
	var $callerId = $( '#modal-session-song-take-caller' ).val();

	if ( $id != "" && $id != undefined ) {
		$.getJSON( '/api/take/session_song/' + $id, function ( result ) {
			$.each( result, function ( index, value ) {
				makeTableRow();

				$( '#modal-session-song-take-title-' + i ).val( value.title );
				$( '#modal-session-song-take-id-' + i ).val( value.id );
				i++;
			} );
		} );
	} else {
		$( '#modal-session-song-take-table' ).find( 'tbody' )
			.append( $( '<tr>' )
				.append( $( '<td>' )
					.attr( 'colspan', 4 )
					.text( 'Error - need to provide a session song id' ) ) );


	}

	function makeTableRow() {
		var j = i;
		$( '#modal-session-song-take-table' ).find( 'tbody' )
			.append( $( '<tr id="modal-session-song-take-table-row-' + j + '">' )
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-session-song-take-id" )
						.attr( "id", "modal-session-song-take-id-" + j )
						.attr( "hidden", true )
					)
					.append( $( '<input>' )
						.addClass( "modal-session-song-take-title" )
						.attr( "id", "modal-session-song-take-title-" + j )
						.attr( "disabled", true )
					)
					.append( $( '<button>' )
						.addClass( "btn-take btn" )
						.attr( "id", "btn-take-edit-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-pencil" )
						)
						.click( function () {
							$( "#div-modal-take" ).load( 'take.html', function () {
								$( '#modal-take-id' ).val( $( '#modal-session-song-take-id-' + j ).val() );
								$( '#modal-take-session-song-id' ).val( $( '#modal-session-song-take-session-song-id' ).val() );
								$( '#modal-take-caller' ).val( j );
								$.getScript( 'js/take.js' );
								$( '#take-modal' ).modal( 'show' );
							} );
						} )
					)
					.append( $( '<button>' )
						.addClass( "btn-take btn" )
						.attr( "id", "btn-take-new-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-plus" )
						)
						.click( function () {
							$( "#div-modal-take" ).load( 'take.html', function () {
								$( '#modal-take-caller' ).val( j );
								$.getScript( 'js/take.js' );
								$( '#take-modal' ).modal( 'show' );
							} );
						} )
					)
					.append( $( '<button>' )
						.addClass( "btn-take btn" )
						.attr( "id", "btn-take-delete-" + j )
						.attr( "type", "button" )
						.attr( "data-toggle", "modal" )
						.append( $( '<span>' )
							.addClass( "glyphicon glyphicon-trash" )
						)
						.click( function () {
							if ( confirm( "Are you sure you want to delete this take? This can't be undone!" ) ) {
								var id = $( '#modal-session-song-take-id-' + j ).val();
								if ( id != null ) {
									$.ajax( {
										url: '/api/take/' + id,
										type: 'DELETE',
										headers: {
											'Accept': 'application/json',
											'Content-Type': 'application/json'
										},
										success: function ( result, status, data ) {
											$( "#modal-session-song-take-table-row-" + j ).remove();
										},
										error: function ( result, status, xhr ) {
											$( "#modal-session-song-take-table-row-" + j ).remove();
										}
									} );
								} else {
									$( "#modal-session-song-take-table-row-" + j ).remove();
								}

							}
						} )
					)
				)
			);
	}

	$( '#modal-session-song-take-add' ).click( function () {
		makeTableRow();
		i++;
	} );
} );