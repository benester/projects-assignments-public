defmodule TakeAndDrop do
    def tak([]) do :no end
    def tak(m) do
        [head|_] = m
        {:ok, head}
    end

    def drp([])do :no end
    def drp(m)do 
        [ _head | tail] = m
        {:ok, tail} 
    end
end