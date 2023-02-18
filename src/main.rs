fn leak() {
    let data = vec![0; 1024];
    std::mem::forget(data);
}

fn main() {
    leak();
}