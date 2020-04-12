defmodule F2 do 
    #rädda funktioner efter crash med detta. Kommer ej ändra datastrukturer vid crash
    #I java kommer datastrukturen ändras.
    def foo(x, y) do
        res = try do   
            bar(x, y)
        rescue
            error ->
                {:caught, error}
        end
        {x, y, res}
    end

    #slå samman listor, det dåliga sättet. Mönster matchar, för att se vad användaren knappar in.
    def append([], y) do y end
    # med rekurssion under, behövs inte dessa.
    
    #def append([h], y) do [h | y] end
    #def append([h1, h2], y) do [h1, h2 | y] end
    #def append([h1, h2, h3], y) do [h1, h2, h3 | y] end
    
    #rekursivt
    def append([h | t], y) do
        res = append(t,y)
        [h | res]
    end
    #alternativt
    def append ([h | t], y) do
        [h | append(t, y)]
    end

    #Fakultet
    def fac(1) do end
    def fac(n) do
        n*fac(n-1)
    end

    #union
    def union([], y) do y end
    def union([h|t], y) do
        union(t, [h|y])
    end

    #sum tail rekursiv 
    def sum(|)do sum(|,0)

    def sum([], s) do s end
    def sum([n|t] , s) do
        sum(t, s+n)
    end
end


#Försök lösa
[h|t] = [:a, [:b,:c]]   -> h = :a, t = [:b, :c]

[h1, h2|t] = [:a,:b,:c] -> h1 = :a, h2 = :b, t = :c

[h1, h2, t] = [:a, :b, :c] -> h1=:a, h2=:b, t=:c 

[h1, h2, t] = [:a ,:b, :c ,:d] -> error, typo. #mönster matchning matchar ej

[h1|[h2|t]] = [:a, :b, :c] -> h1 = :a h2 = :b t = [:c]

[h|t] = [:a | b ] -> h= :a, t= :b

[4 | [3 | 2 [ | 1| []]]] = [4,3,2,1]

h = :a; t = [:b]; [h|t] = [:a, :b]
h =:a; t= [[:b]]; [h|t] = [:a [:b]]

h = [:a ,:b]; t=[:c, :d]; [h|t] = [[:a, :b], :c, :d]
h = [:a ,:b]; t=[:c, :d]; [h|t] = [[:a, :b], [:c, :d]]

h1 = [:a ,:b]; h2=[:c, :d]; t = [:e, :f]; [h1 | [h2|t]] = [[:a, :b], [:c, :d], :e, :f]
h1 = [:a ,:b]; h2=[:c, :d]; t = [:e, :f]; [h1 , [h2|t]] = [[:a, :b], [[:c, :d], :e, :f]]

