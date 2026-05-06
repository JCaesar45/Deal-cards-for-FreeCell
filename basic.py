def dealFreeCell(seed):
    ranks = ['A', '2', '3', '4', '5', '6', '7', '8', '9', 'T', 'J', 'Q', 'K']
    suits = ['C', 'D', 'H', 'S']
    cards = [r + s for r in ranks for s in suits]
    
    state = seed
    deal_order = []
    
    for length in range(52, 0, -1):
        state = (214013 * state + 2531011) & 0x7fffffff
        rand_val = state >> 16
        j = rand_val % length
        
        # Swap with last
        cards[j], cards[length - 1] = cards[length - 1], cards[j]
        # Take the chosen card
        deal_order.append(cards.pop())
    
    # Group into rows (as shown in the examples)
    board = []
    for i in range(0, 52, 8):
        board.append(deal_order[i:i + 8])
    
    return board
