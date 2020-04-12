defmodule Tentamen do
    #------------------------------------------------------------------#
    ##Drop every "nr" elements from the list
    def drop(list, nr)do
        drop(list, nr, 1)
    end
    def drop([], _, _) do [] end
    def drop([_head | tail], nr, nr) do
        drop(tail, nr, 1)
    end
    def drop([head | tail], nr, acc)do
        [head | drop(tail, nr, acc + 1)]  
    end
    #------------------------------------------------------------------#
    #Rotate a list n steps
    def rotate(list, steps) do rotate(list, steps, []) end
    def rotate(list, 0, acc)do list ++ Enum.reverse(acc) end #++ representerar append(list, reverse(acc))
    def rotate([head | rest], n, acc)do 
        rotate(rest, n-1, [head | acc])
    end
    #---------------------------------------------------------------------#
    #Pascals triangle will return the n:th row. ex pascal(4) -> [1,4,6,4,1]
    def pascal(1) do [1] end
    def pascal(n) do
        prev = pascal(n-1)
        seq(prev, n-2, [1])
    end
    def seq(prev, 0, acc) do acc ++ [1] end
    def seq([first, second | rest], n, acc) do
        seq([second | rest], n-1, acc ++ [first + second])
    end

    #-----------------------------------------------------------------------#
    #Polish notation, ex 2 3 + will give 5
    def hp35([first|rest])do 
        hp35(rest, [first])
    end

    def hp35([], stack) do stack end

    def hp35([head | tail], stack)do
        cond do
            head == :add ->
                [first, second] = stack
                hp35(tail, [first + second])
            head == :sub ->
                [first, second] = stack
                hp35(tail, [first - second])
            true ->
                hp35(tail, stack ++ [head])
        end
    end
    #----------------------------------------------------------------------------#
    #Find the nth leaf, if nth leaf doesn't exist, return {:cont, n-k} where k is the steps needed to get to n
    def nth(n, tree)do
        nth(n, tree, 1)
    end

    def nth(n, {:leaf, c} , n) do 
        {:found, c} 
    end
    def nth(n, {:node, {:leaf, a}, right}, m) do
        cond do
            n == m
                -> {:found, a}
            true -> 
                nth(n, right, m + 1)
        end
    end
    def nth(n, {:leaf, a}, n) do {:found, a} end

    def nth(n, {:leaf, _d }, m) do {:cond, n-m} end

    def nth(n, {:node, left, right}, m)do
        left = nth(n, left, m)
        case left do
            {:found, _d}
                -> left
            {_, val} ->
                nth(n, right, n-val + 1)
        end
    end
    #---------------------------------------------------------------------------------#
    end 
defmodule Tentamen2 do
    #---------------------------------------------------------------------------------#
    #send in list of touples, {char, n} where n is the number of occurances in a row.
    def decode([]) do [] end
    def decode([first | rest])do
        sequence(first) ++ decode(rest)
    end

    def sequence({_, 0}) do [] end
    def sequence({char, occ})do
        [char | sequence({char, occ - 1})]
    end
    #----------------------------------------------------------------------------------#
    #combine two lists, ex [:a, :b, :c], [1, 2, 3] -> ({:a, 1}, {:b, 2}, {:c, 3})

    def zip(list1, list2)do
        zip(list1, list2, [])
    end
    def zip([], [], ziped) do Enum.reverse(ziped) end

    def zip([first | rest], [head | tail], ziped) do
        zip(rest, tail, [{first, head} | ziped])
    end

    #-------------------------------------------------------------------------------------#
    #Implement fizzbuzz
    def fizz(n)do fizz(1, n + 1, 1, 1) end
    
    def fizz(n, n, _, _)do [] end
    def fizz(number, base, 3, 5) do
        [:fizzbuzz | fizz(number + 1, base, 1, 1) ]
    end
    def fizz(number, base, 3, b) do
        [:fizz | fizz(number + 1, base, 1, b + 1)]
    end
    def fizz(number, base, f, 5)do
        [:buzz | fizz(number + 1, base, f + 1, 1)]
    end
    def fizz(number, base, f, b)do
        [number | fizz(number + 1, base, f + 1, b + 1)]
    end
    end
defmodule Tentamen3 do
    def transF(x,y, [])do [] end
    def transF(x,y,[x | rest])do
        transF(x, y, rest)
    end
    def transF(x,y,[head | rest]) do
        [(head * y) | transF(x, y, rest)]
    end

    def sum(l) do 
        sum(l, 0)
    end
    def sum([], s) do s end
    def sum([head | tail], s)do
        sum(tail, head + s)
    end
    end
defmodule T4 do
    def remit(tree, n)do
        f = fn(v) -> rem(v,n) end
        trans(tree, f) 
    end
    def trans(:nil, f) do :nil end 
    def trans({:node, val, left, right}, f)do
        {:node, f.(val), trans(left, f), trans(right, f)}
    end
    
    def start()do
        spawn(fn() ->hp35([]) end)
    end
    def hp35(stack)do
        receive do
            {:add, from} ->
                [v1, v2 | rest] = stack
                res = v1 + v2
                send(from, {:res, res})
                hp35([res | rest])
            {:int, int} ->
                hp35([int | stack])
        end
    end
    def test()do
        pid = start()
        send(pid, {:int, 4})
        send(pid, {:int, 18})
        send(pid, {:add, self()})
        receive do
            {:res, res}-> 
                send(pid, {:int, 5})
                send(pid, {:add, self()})
                receive do
                    {:res, res} ->
                        res
                end
        end 
    end
    end
defmodule T5 do
    def fib() do
        fib(1,0)
    end
    def fib(tal1, tal2)do
        fn()-> {:ok, tal1, fib(tal1 + tal2, tal1)} end
    end
    def take(seq, n)do

    end

    def test()do
        cont = fib()
        {:ok, f1, cont} = cont.()
        {:ok, f2, cont} = cont.()
        {:ok, f3, cont} = cont.()
        [f1,f2,f3]
    end

    def fac(1) do 1 end
    def fac(n)do n * fac(n-1) end

    def facl(1) do [1] end
    def facl(n) do
        rest = facl(n-1)
        [f| _] = rest
        [n*f| rest]
    end
    end
defmodule T6 do
    def seven([])do [] end
    def seven([head| tail]) do
        cond do
            head == ?å ->
                [?} | seven(tail)]
            head == ?ä ->
                [?{ | seven(tail)]
            head == ?ö ->
                [?| | seven(tail)]
            true ->
                [head | seven(tail)]
            end
    end

    @type tree() :: {:node, any(), tree(), tree()} | :nil

    def traverse(:nil) do [] end
    def traverse({:node, val, left, right})do
        traverse(left) ++ [val | traverse(right)]
    end

    def better(tree)do
        traverse(tree, []) 
    end
    def traverse(:nil, sofar)do sofar end
    def traverse({:node, val, left, right}, sofar)do
        traverse(left, [val | traverse(right, sofar)])
    end
    
    def insert(:nil, value)do {:node, value, :nil, :nil} end

    def insert({:node, val, left, right}, value)do
        cond do 
            value < val ->
                {:node, val, insert(left, value), right}
            value > val -> 
                {:node, val, left, insert(right, value)}
            value == val ->
                {:node, val, left, right}
        end 
    end

    def delete({:node, n, :nil, :nil}, n)do
        :nil
    end
    def delete({:node, n, :nil, right}, n)do
        right
    end
    def delete({:node, n, left, :nil}, n)do
        left
    end
    def delete({node, n, left, right}, n)do
        val = rightmost(left)
        {:node, val, delete(left, val), right}
    end
    def rightmost({:node, n, _, :nil})do n end
    def rightmost({:node, _n, _left, right})do
        rightmost(right)
    end
    def delete({:node, n, left, right}, val)do
        cond do
            val < n ->
                {:node, n, delete(left, val)}
            val > n ->
                {:node, n, left, delete(right, val)}
            true ->
                {:node, n, left, right}
        end
    end
    end
defmodule Concurr do
    def create()do
        spawn(fn() -> open() end)
    end

    def open()do
        receive do
            {:take, pid} ->
                send(pid, :granted)
                closed()
        end
        open()
    end
    def closed()do
        receive do
            {:release} ->
                open()
        end
    end
    def proc(lock)do
        send(lock, {:take, self()})
            receive do
                :granted ->
                    T6.insert({:node, 2,:nil, :nil}, 4)
            end
        send(lock, {:release, self()})
    end
    end
defmodule Exercism do
    def beerSong(0)do
        n = 99
        IO.puts("No more bottles of beer on the wall, no more bottles of beer.")
        IO.puts("Go to the store and buy some more, #{n} bottles of beer on the wall.")
        end
    def beerSong(n)do
        IO.puts("#{n} bottles of beer on the wall, #{n} bottles of beer.") 
        n = n-1
        IO.puts("Take one down and pass it around, #{n} bottles of beer on the wall.")
        beerSong(n)
        end

    end
defmodule Bowling do
    def throw()do
        f1 = random(10)
        cond do
            f1 < 10 ->
                f2 = random(10 - f1)
                cond do
                    f1 + f2 == 10 ->
                        {f1, 'spare'}
                    true ->
                        {f1, f2}
                end
            f1 == 10 ->
                {'strike'}
        end
    end
    def finalThrow()do
        f1 = throw()
        cond do
            f1 =={'strike'} ->
                f2 = throw()
                cond do
                    f2 == {'strike'} ->
                        f3 = throw()
                        cond do 
                            f3 == {'strike'} ->
                                {'strike', 'strike', 'strike'}
                            true ->
                                {f1, _} = f3
                                {'strike', 'strike', f1}
                        end
                    true->
                        {t1, t2} = f2
                        {'strike', t1, t2}
                end
            true ->
                {t1, t2} = f1
                cond do
                    t2 == 'spare'->
                        f2 = throw()
                        cond do
                            f2 == {'strike'} ->
                                {f1, 'strike'}
                            true ->
                                {t11, t22} = f2
                                {t1,t2, t11}
                        end
                    true ->
                        f1
                end   
        end 
    end
    def score([])do 0 end
    def score([{t1, t2} | tail]) do
        cond do
            t2 == 'spare' ->
                10 + spare(tail) + score(tail)
            true ->
                t1 + t2 + score(tail)
        end
    end
    def score([{'strike'} | tail])do
       10 +  strike(tail) + score(tail)
    end
    def score([{t1, t2, t3}])do
        scoreSingle(t1) + scoreSingle(t2) + scoreSingle(t3)
    end

    def scoreSingle(n)do
        cond do
            n == 'strike'->
                10
            n == 'spare' ->
                10
            true -> n
        end
    end
    def strike([{'strike'}, {t1,_,_}])do
        10 + scoreSingle(t1)
    end
    def strike([{t1,t2,_}])do
        scoreSingle(t1) + scoreSingle(t2)
    end
    def strike([{'strike'}, {'strike'} | rest])do
        20
    end
    def strike([{'strike'}, {t1, _} | rest])do
        10 + t1
    end
    def strike([{t1, t2}])do  
        cond do    
            t2 == {'spare'} -> 
                10
            true ->
                t1 + t2
        end
    end
    def strike([{t1, t2} | rest])do
        cond do
            t2 == 'spare' ->
                10
            true ->
                t1 + t2
        end
    end
    def spare([])do 0 end 
    def spare([{'strike'}])do
        10
    end
    def spare( [{n1,_} | rest] )do
        n1
    end
    def play(0)do
        [finalThrow()]
    end
    def play(n)do
        [throw()| play(n-1)]
    end
    def play()do
        round = play(9)
        IO.inspect(round)
        score(round)
    end

    def random(max)do
        Enum.random(0..max)
    end
    end
defmodule Erlang do

    def reduce([head | tail])do
        [head | reduce(tail, head)]
    end
    def reduce([], _) do [] end
    def reduce([head | tail], prev)do
        cond do
            head == prev ->
                reduce(tail, prev)
            true ->
                [head | reduce(tail, head)]
        end
    end
    def encode([]) do [] end
    def encode([head | tail])do 
        [threeStepsBack(head) | encode(tail)]
    end
    def threeStepsBack(char)do
        cond do
            char == 32 ->
                32
            char == 97 ->
                120
            char == 98 -> 
                121
            char == 99 ->
                122
            true ->
                char - 3
        end
    end
    #@type card() :: {val(), face()}
    #@type val() :: Integer()
    #@type face() :: "heart" | "Diamond" | "spades" | "clubs"
    
    def triss(hand) do
        triss(hand, hand)
    end
    def triss(hand, []) do false end
    def triss(completeHand, [head | tail])do
        {value, _} = head
        case three(Enum.filter(completeHand, fn(x) -> {val, _} = x; val == value end)) do
            true -> true
            false -> triss(completeHand, tail)
        end
    end
    def three([_,_,_])do
        true
    end
    def three([_,_,_,_])do
        true
    end
    def three([_,_,_,_,_])do
        IO.puts('du fuskar!')
        true
    end
    def three(_)do
        false
    end
    end