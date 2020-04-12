defmodule Huffman do
    def sample do
        read("Longtext.txt", 500000)
    end

    def read(file, n) do
        {:ok, file} = File.open(file, [:read])
        binary = IO.read(file, n)
        File.close(file)

        case :unicode.characters_to_list(binary, :utf8) do
            {:incomplete, list, _} ->
                list
            list ->
                list
        end
    end

    def text()  do
        'A long string of character that may or may not be more efficient right now'
    end

    def test do
        text = text()
        sample = sample()
        tree= tree(sample)
        encode = encode_table(tree)
        IO.inspect(tree, limit: :infinity)
        IO.inspect(encode , limit: :infinity)
        seq = encode(text, encode)
        IO.inspect(seq, limit: :infinity)
        decode(seq, encode)
    end

    #create a Huffman tree given a sample text.
    def tree(sample) do
        freqlist = freq(sample)
        sortedList = sortfreq(freqlist, [])
        buildTree(sortedList)
    end

    def buildTree([{elem1, freq1}, {elem2, freq2}| rest])do
        newNode = {{elem1 , elem2}, freq1 + freq2}
        buildTree(insert(newNode, rest))
    end
    def buildTree([element]) do
        element
    end

    def sortfreq([first|rest], []) do
        sortfreq(rest, insert(first, []))
    end

    def sortfreq([],sorted) do
        sorted
    end
    def sortfreq([first|rest], sorted) do
        sortfreq(rest, insert(first, sorted))
    end
    def insert(element, []) do [element] end
    def insert(element, [first | rest]) do 
        {_, freq} = first
        {_, elementFreq} = element
        cond do
            elementFreq > freq ->
                [first | insert(element, rest)]
            true ->
                [element, first|rest]
        end
    end

    #create an encoding table containing the mapping from characters to codes given a Huffman tree.
    def encode_table({left,_right}) do
        encode_table(left, [])
    end
    def encode_table({left,right}, acc) do
        list = [encode_table(left, (acc ++ [0])), encode_table(right, (acc ++ [1]))]
        List.flatten(list)
    end
    
    def encode_table(number, acc) do
        cond do
            is_integer(number)->
                {number, acc}
            true ->
                encode_table(number, acc)
        end
    end
    #create an decoding table containing the mapping from codes to characters given a Huffman tree.
    def decode([], _) do
        []
    end
    def decode(seq, table) do
        {char, rest} = decode_char(seq, 1, table)
        [char | decode(rest, table)]
    end

    def decode_char(seq, n, table) do
        {code, rest} = Enum.split(seq, n)

        case List.keyfind(table, code, 1) do
            {char, _} ->
                {char, rest}
            nil ->
                decode_char(seq, n+1, table)
        end
    end
    #encode the text using the mapping in the table, return a sequence of bits.
    def encode([], _)do
       []
    end
    def encode(text, table) do
        [first | rest] = text
        list = [lookup(first, table) | encode(rest, table)]
        List.flatten(list)
    end
    def lookup(char, [{charVal, path} | tail]) do
        cond do 
            char == charVal ->
                path
            true ->
                lookup(char, tail) 
        end
    end
    def lookup(char, {char, path}) do path end

    def freq(sample) do freq(sample, []) end

    def freq([], freq) do freq end

    def freq([char | rest], freq) do
        freq(rest, addFreq(char, freq))
    end

    def addFreq(char, [])do [{char, 1}] end

    def addFreq(char, [first | rest])do
        {charTouple , freq} = first
        cond do
            charTouple == char ->
                    [{charTouple, freq + 1} | rest]
            true ->
                [first | addFreq(char, rest)]
        end
    end
end