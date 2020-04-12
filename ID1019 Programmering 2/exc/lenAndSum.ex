defmodule LenAndSum do
    def len([]) do 0 end
    
    def len(m) do
        [_head | tail] = m
        len(tail, 1)
    end

    def len(m, n)do
        cond do 
            m == []  -> 
                n
            true ->
                [_head|tail] = m 
                len(tail, n+1)
        end
    end
    
    def sum([]) do 0 end
    def sum(m) do
        [head|tail] = m
        sum(tail, head)
    end

    def sum(m, n) do 
        cond do 
            m == [] ->
                n
            true ->
                [head | tail] = m
                sum(tail, head + n)
        end
    end
end