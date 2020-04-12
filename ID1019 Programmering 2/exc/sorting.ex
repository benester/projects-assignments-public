defmodule Sorting do
    
    def insert(element, [head|tail])do
        cond do
            element < head ->
                [element | [head|tail]]
            element>head ->
                [head | insert(element, tail)]  
            true ->         
                [element | [head|tail]] 
        end
    end
    def insert(element, [])do [element]end

    def isort (l)do isort(l, [])end
    def isort([head | tail], sorting)do
        isort(tail, insert(head, sorting))
    end
    def isort([],sorted)do
        sorted
    end
end