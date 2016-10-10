$( document ).ready( function () {

	var i = 1;

	$.getJSON( '/api/book_string', function ( result ) {
		$.each( result, function ( index, value ) {
			makeTableRow();

			$( '#modal-book-string-id-' + i ).val( value.id );
			$( '#modal-book-string-name-' + i ).val( value.name );
			$( '#modal-book-string-value-' + i ).val( value.value );
			i++;
		} );
	} );

	function makeTableRow() {
		var j = i;
		$( '#modal-book-string-table' ).find( 'tbody' )
			.append( $( '<tr>' )
				.append( $( '<td>' )
					.append( $( '<input>' )
						.addClass( "modal-book-string-id" )
						.attr( "id", "modal-book-string-id-" + j )
						.attr( "hidden", true )
					)
					.append( $( '<input>' )
						.addClass( "modal-book-string-name" )
						.attr( "id", "modal-book-string-name-" + j )
						.attr( "type", "text" )
						.attr( "disabled", "true" )
					)
				)
				.append( $( '<td>' )
					.append( $( '<textarea>' )
						.addClass( "modal-book-string-value" )
						.attr( "id", "modal-book-string-value-" + j )
					)
				)
			);
	}

	$( '#modal-book-string-submit' ).click( function () {
		$( '.modal-body-success' ).empty();
		for ( var k = 1; k < i; k++ ) {
			var id = $( '#modal-book-string-id-' + k ).val();
			var name = $( '#modal-book-string-name-' + k ).val();
			var value = $( '#modal-book-string-value-' + k ).val();

			var object = { "id": id, "name": name, "value": value };

			ajaxRequest( '/api/book_string/' + id, 'PUT', object, $( '#modal-book-string-success' ), $( '#modal-book-string-error' ), processSubmit );
		}
	} );

	function processSubmit( data ) {
		$( '.modal-body-success' ).append( $( '<p>' ).text( "Book string with name " + data.name + " saved, id is " + data.id ) );
	}

} );