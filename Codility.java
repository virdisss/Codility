//Lesson 1 - BinaryGap
int solution(int N) {

    int max = 0, count = 0;

    String number = Integer.toBinaryString(N);

    for (int i = 0; i < number.length(); i++) {

        if (number.charAt(i) == '0') {
            count++;
        } else {

            if (count > max) {
                max = count;
            }
            count = 0;
        }
    }
    return max;
}

//Lesson 2 - OddOccurrencesInArray
public int solution(int[] A) {

    Arrays.sort(A);
    int current = A[0];
    int count = 1;

    for (int i = 1; i < A.length-1; i++) {

        if (current != A[i]) {

            if (count <= 1 || count % 2 == 1) {
                return current;
            } else {
                current = A[i];
                count = 1;
            }
        } else {
            count++;
        }
    }
    return A[A.length-1];
}

//Lesson 2 - CyclicRotation
public int[] solution(int[] A, int K) {

    int length = A.length;
    int[] res = new int[length];

    for (int i = 0; i < length; i++) {

        int j = (i + K) % length;
        res[j] = A[i];
    }
    return res;
}

//Lesson 3 - TapeEquilibrium
public int solution(int[] A) {

    int[] reserveSums = new int[A.length];
    int[] sums = new int[A.length];
    int length = A.length;
    int s1 = 0;
    int s2 = 0;

    for (int i = 0; i < length; i++) {
        s1 += A[i];
        sums[i] = s1;
        s2 += A[length - (i + 1)];
        reserveSums[length - (i + 1)] = s2;
    }

    int min = Math.abs(sums[0] - reserveSums[1]);

    for (int i = 2; i < length; i++) {
        int tmp = Math.abs( reserveSums[i] - sums[i-1] );

        if (min > tmp) {
            min = tmp;
        }
    }
    return min;
}

//Lesson 3 - PermMissingElem
int solution(int A[]) {

    int sum = 0;
    int total = 0;

    for (int i = 1; i <= (A.length+1); i++) {
        total += i;
    }

    for (int i = 0; i < A.length; i++) {
        sum += A[i];
    }
    return (total - sum);
}

//Lesson 3 - FrogJmp
int solution(int X, int Y, int D) {

    int distance = Y-X;
    int jumps = distance/D;
    if ((distance % D) != 0) {
        jumps++;
    }
    return jumps;
}

//Lesson 4 - MissingInteger
int solution(int[] A) {

    int positionIndex = -1;

    if (A.length == 0) {
        return 1;
    } 
    if (A.length == 1) {

        if (A[0] > 1 || A[0] <= 0) {
            return 1;
        } else {
            return A[0] + 1;
        }
    }
    Arrays.sort(A);
    for (int i = 0; i < A.length; i++) {

        if (A[i] > 0) {
            positionIndex = i;
            break;
        }
    }

    int start = 1;
    int i = 0;

    if (positionIndex == -1) {
        return 1;
    } else if (positionIndex > 0) {
        i = positionIndex;
    }

    for (; i < A.length-1; i++) {

        if ((A[i]) != A[i+1]) {

            int n = A[i];
            if (start != n) {
                return start;
            }
            start++;
        }
    }
    return (start == A[i]) ? ++start : start;
}

//Lesson 4 - PermCheck
int solution(int[] A) {

    Arrays.sort(A);
    for (int i = 1; i <= A.length; i++) {

        if (A[i-1] != i) {
            return 0;
        }
    }
    return 1;
}

//Lesson 4 - FrogRiverOne
public int solution(int X, int[] A) {

    int max = 0;
    //We add one to the array in case where X is equal to A.length
    int[] indexes = new int[A.length + 1];

    if (X > A.length) {
        return -1;
    }

    for (int i = 1; i <= X; i++) {
        indexes[i] = 1;
    }

    for (int i = 0; i < A.length; i++) {

        if (indexes[A[i]] == 1) {
            max = i;
            indexes[A[i]] = -1;
        }
    }

    for (int i = 1; i <= X; i++) {

        if (indexes[i] == 1) {
            return -1;
        }
    }
    return max;
}

//Lesson 4 - MaxCounters
int[] solution(int N, int A[]) {
        
    int max = 0;
    int[] operations = new int[N];
    List<Integer> list = new ArrayList<>();
    list.add(A[0]);
        
    for (int i = 1; i < A.length; i++) {
            
        if (A[i] != A[i-1] || (A[i] == A[i-1] && A[i] <= N)) {
            list.add(A[i]);
        } 
    }
        
    for (int i = 0; i < list.size(); i++) {
            
        if (list.get(i) > N) {
            for (int j = 0; j < N; j++) {
                operations[j] = max;
            }
        } else {
                
            if (++operations[list.get(i)-1] > max) {
                max = operations[list.get(i)-1];
            }
        }
    }
    return operations;
}

//Lesson 5 - PassingCars
int solution(int A[]) {
        
    int count = 0, x = 0, y = 0, k = 0;
    int eastSize = (int)Arrays.stream(A).filter(p->p == 0).count();
    int westSize = A.length - eastSize;
    int[] easts = new int[eastSize];
    int[] wests = new int[westSize];
    boolean skip = false;

    for (int i = 0; i < A.length; i++) {
            
        if (A[i] == 0) {
            easts[x++] = i;
        }
        else {
            wests[y++] = i;
        }
    }
        
    if (easts.length == 0 || wests.length == 0) {
        return 0;
    }
        
    for (int i = 0; i < easts.length; i++) {
            
        for (int j = k; j < wests.length; j++) {
                
            if (easts[i] < wests[j]) {
                count++;
                if ( skip ) {
                    k = j;
                    skip = false;
                }
            } else {
                skip = true;
            }
        }
    }
    return (count > 1000000000 ? -1 : count);
}

//Lesson 5 - MinAvgTwoSlice
public int solution(int[] A) {
        
    double min = (double)((A[0] + A[1]))/2;
    int i_min = 0;
    for (int i = 0; i < A.length; i++) {
            
        if (i+1 < A.length) {
                
            double tmp1 = (double)(A[i] + A[i+1])/2;
            double res = tmp1;
            if (i+2 < A.length) {
                double tmp2 = (double)(A[i] + A[i+1] + A[i+2])/3;
                res = (tmp1 < tmp2) ? tmp1 : tmp2;
            }
            if (min > res) {
                min = res;
                i_min = i;
            }
        }
    }
    return i_min;
}

//Lesson 5 - GenomicRangeQuery
int[] solution(String S, int[] P, int[] Q) {
        
    int N = P.length;
    int[] A = new int[S.length()];
    int[] C = new int [S.length()];
    int[] G = new int[S.length()];
    int[] T = new int [S.length()];
    int[] result = new int[N];
        
    for (int i = 0; i < S.length(); i++) {
            
        A[i] += (S.charAt(i) != 'A') ? 0 : 1;
        C[i] += (S.charAt(i) != 'C') ? 0 : 1;
        G[i] += (S.charAt(i) != 'G') ? 0 : 1;
        T[i] += (S.charAt(i) != 'T') ? 0 : 1;
    }
        
    for (int i = 0; i < N; i++) {
            
        if (Q[i] == P[i]) {
            char c = S.charAt(Q[i]);
            if (c == 'A') {
                result[i] = 1;
            } else if (c == 'C') {
                result[i] = 2;
            } else if (c == 'G') {
                result[i] = 3;
            } else if (c == 'T') {
                result[i] = 4;
            }
        } else {
            int a = A[Q[i]] - A[P[i]];
            int c = C[Q[i]] - C[P[i]];
            int g = G[Q[i]] - G[P[i]];
            result[i] = a > 0 ? 1 : c > 0 ? 2 : g > 0 ? 3 : 4;
        }
    }
    return result;
}

//Lesson 6 - Triangle
int solution(int A[]) {
        
    if (A.length < 3) {
        return 0;
    }
        
    Arrays.sort(A);
        
    for (int i = 0; i < A.length-2; i++) {
            
        if (A[i] <= 0) {
            continue;
        } else {
                
            int P = A[i];
            int Q = A[i + 1];
            int R = A[i + 2];
                    
            if ((P + Q) > R && (P + R) > Q && (R + Q) > P) {
                return 1;
            } else {
                    
                if (P == R && P == Q && Q == R) {
                    return 1;
                }
            }
        }
    }
    return 0;
}

//Lesson 6 - MaxProductOfThree
public int solution(int[] A) {

    Arrays.sort(A);
    int[] negatives = Arrays.stream(A).filter(p->p < 0).toArray();

    if (negatives.length > 1) {

        int[] positives = Arrays.stream(A).filter(p->p > 0).toArray();

        if (positives.length == 0) {
            return A[A.length-1] * A[A.length-2] * A[A.length-3];
        }

        int np = negatives[0] * negatives[1];

        if (positives.length > 2) {

            if (np * positives[positives.length - 1] > A[A.length-1] * A[A.length-2] * A[A.length-3]) {
                return np * positives[positives.length - 1];
            } else {
                return positives[positives.length - 1] * positives[positives.length - 2] * positives[positives.length - 3];
            }

        } else {
            if (positives.length == 2) {

                if (np  > A[A.length-1] * A[A.length-2]) {
                    return np * positives[positives.length - 1];
                }
                return positives[0] * positives[1] * negatives[negatives.length-1];
            } else {
                return positives[0] * negatives[negatives.length - 2] * negatives[negatives.length-1];
            }
        }
    }
    return A[A.length-1] * A[A.length-2] * A[A.length-3];
}

//Brackets
public int solution(String S) {
    // write your code in Java SE 8
    int[] count = {0, 0, 0};

    if (S.isEmpty()) {
        return 1;
    } else {

        if (S.startsWith(")") || S.startsWith("]") || S.startsWith("}")) {
            return 0;
        }

        for (int i = 0; i < S.length()-1; i++) {

            if (S.charAt(i) == '(' && (S.charAt(i + 1) == '}' || S.charAt(i + 1) == ']')) {
                return 0;
            } else if (S.charAt(i) == '{' && (S.charAt(i + 1) == ')' || S.charAt(i + 1) == ']')) {
                return 0;
            } else if (S.charAt(i) == '[' && (S.charAt(i + 1) == ')' || S.charAt(i + 1) == '}')) {
                return 0;
            }
        }

        for (int i = 0; i < S.length(); i++) {

            if (S.charAt(i) == '(') { //
                count[0]++;
            } else if (S.charAt(i) == '{') {
                count[1]++;
            } else if (S.charAt(i) == '[') {
                count[2]++;
            } else if (S.charAt(i) == ')') {
                count[0]--;
            } else if (S.charAt(i) == '}') {
                count[1]--;
            } else if (S.charAt(i) == ']') {
                count[2]--;
            }
            if (count[0] < 0 || count[1] < 0 || count[2] < 0) {
                return 0;
            }
        }

        for (int i = 0; i < count.length; i++) {

            if (count[i] != 0) {
                return 0;
            }
        }
    }
    return 1;
}