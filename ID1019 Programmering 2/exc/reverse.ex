defmodule Reverse do

    def reverse(l) do
       reverse(l, []) 
    end
    def reverse([head|tail], l2)do
        reverse(tail, [head | l2])
    end
    def reverse([], reversed)do
        reversed
    end
end

