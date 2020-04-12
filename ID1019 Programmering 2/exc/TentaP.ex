defmodule Tenta1 do
    def msort([]) do [] end
    def msort([a]) do [a] end
    def msort(a) do
        {l1, l2} = split(a)
        merge(msort(l1), msort(l2))
    end
    def split(list)do
        len = round(length(list)/2)
        {a, b} = Enum.split(list, len)
        {b, a}
    end
    def merge([],[])do [] end
    def merge([], list)do list end
    def merge(list, [])do list end

    def merge([head | tail], [head2 | tail2])do
        cond do
            head < head2 ->
                [head | merge(tail, [head2 | tail2])]
            head2 < head ->
                [head2 | merge(tail2, [head|tail])]
            true ->
                [head | merge(tail, [head2 | tail2])]
        end
    end

    def heap_to_list({:node, el, left, right})do
        [el | smallest(left,right)]
    end
    def smallest(:nil, :nil)do [] end
    def smallest(:nil, {:node, el, left, right})do 
        [el | smallest(left, right)]
    end
    def smallest({:node, el, left, right}, :nil)do 
        [el, smallest(left, right)]
    end
    def smallest({:node, el1, left1,right1}, {:node, el2, left2, right2})do
        cond do
            el1 < el2 ->
                [el1 | merge(merge(smallest(left1, right1), smallest(left2, right2)), [el2])]
            el2 < el1 ->
                [el2 | merge(merge(smallest(left1, right1), smallest(left2, right2)), [el1])]
        end
    end
    def pop(heap)do
        cond do
            heap == :nil
                -> false
            true ->
                {:node, val, left, right} = heap
                {:ok, val, newHeap(left,right)}
                
        end
    end

    def newHeap(:nil, :nil)do :nil end
    def newHeap(heap, :nil)do heap end
    def newHeap(:nil, heap)do heap end 
    def newHeap({:node, val, left, right}, {:node, val2, left2, right2})do
        cond do
        val < val2 ->
            {:node,val, newHeap(left, right), {:node, val2, left2, right2}}
        true ->
            {:node,val2, newHeap(left, right), {:node, val, left, right}}
        end
    end 

    def new(id, brand, color)do
        {:car, id, brand, color}
    end
    def color({:car, _id, _brand, color})do
        color
    end
    def paint({:car, id, brand, _oldColor}, newColor)do
        {:car, id, brand, newColor}
    end
    def once(list)do
        once(list, {0, 0})
    end
    def once([], sumNlen)do sumNlen end
    def once([head | tail], {sum, len})do
        once(tail, {sum + head, len + 1})
    end
    def ack(m,n)do
        cond do
            m == 0 ->
                n+1
            true ->
                cond do
                    n == 0 ->
                        ack(m-1, 1)
                    n > 0 ->
                        ack(m - 1, ack(m, n-1))
                end
        end
    end
    def eval([res])do res end
    def eval([operand, operator, operand2 | tail])do
        cond do
            operator == '+' ->
                eval([operand + operand2 | tail])
            operator == '-' ->
                eval([operand - operand2 | tail])
        end
    end
    def isomorfic({:node, _, _left, _right}, :nil)do false end
    def isomorfic(:nil, {:node, _, _left, _right})do false end
    def isomorfic(:nil, :nil)do true end
    def isomorfic({:node, _, left, right}, {:node, _, left2, right2})do 
        leftIso = isomorfic(left, left2)
        rightIso= isomorfic(right, right2)
        cond do
            rightIso == false ->
                false
            leftIso == false ->
                false
            true ->
                true
        end
    end
end