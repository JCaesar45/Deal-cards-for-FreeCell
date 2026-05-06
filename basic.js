
function dealFreeCell(seed) {

  const ranks = ['A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K'];

  const suits = ['C', 'D', 'H', 'S'];

  let cards = [];

  for (let rank of ranks) {

    for (let suit of suits) {

      cards.push(rank + suit);

    }

  }

  let state = seed;

  let dealOrder = [];

  for (let length = 52; length > 0; length--) {

    state = (214013 * state + 2531011) & 0x7fffffff;

    const rand = state >>> 16;

    const j = rand % length;

    // Swap

    [cards[j], cards[length - 1]] = [cards[length - 1], cards[j]];

    dealOrder.push(cards.pop());

  }

  // Group into rows for the board

  const board = [];

  for (let i = 0; i < dealOrder.length; i += 8) {

    board.push(dealOrder.slice(i, i + 8));

  }

  return board;

}
