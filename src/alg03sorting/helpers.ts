export function exchange<T>(array: T[], index1: number, index2: number): void {
    const value1 = array[index1];
    array[index1] = array[index2];
    array[index2] = value1;
}

/**
 * The distribution is sufficiently close to uniform.
 * @param min inclusive
 * @param max exclusive
 */
function getRandomInt(min: number, max: number) {
    min = Math.ceil(min);
    max = Math.floor(max);
    return Math.floor(Math.random() * (max - min)) + min;
}

export function shuffle<T>(array: T[]) {
    for (let i = 0; i < array.length; i++) {
        const randomIndex = getRandomInt(i, array.length - i);
        const swap = array[i];
        array[i] = array[randomIndex];
        array[randomIndex] = swap;
    }
}
